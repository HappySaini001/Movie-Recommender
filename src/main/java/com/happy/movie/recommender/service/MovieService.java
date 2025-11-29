package com.happy.movie.recommender.service;

import com.happy.movie.recommender.model.Movie;
import com.happy.movie.recommender.repository.MovieRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // --- 1. DATABASE LOADING LOGIC ---

    @PostConstruct
    public void init() {
        long count = movieRepository.count();
        if (count == 0) {
            System.out.println("⚠️ Database is empty. Starting migration from CSV to MySQL...");
            loadCsvToDatabase();
        } else {
            System.out.println("✅ Database is ready! Found " + count + " movies.");
        }
    }

    private void loadCsvToDatabase() {
        try {
            Reader in = new InputStreamReader(new ClassPathResource("main_data.csv").getInputStream());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim()
                    .parse(in);

            List<Movie> movieList = new ArrayList<>();
            for (CSVRecord record : records) {
                String title = record.get("movie_title");
                String director = record.get("director_name");
                String comb = record.get("comb");

                // Handle potential missing columns gracefully
                if(title != null) {
                    movieList.add(new Movie(title, director, comb));
                }
            }
            movieRepository.saveAll(movieList);
            System.out.println("🎉 SUCCESS: Migrated " + movieList.size() + " movies to the database.");

        } catch (Exception e) {
            System.err.println("❌ ERROR loading CSV: " + e.getMessage());
        }
    }

    // --- 2. METHODS CALLED BY CONTROLLER ---

    public List<String> getSuggestions() {
        return movieRepository.findAll().stream()
                .map(movie -> capitalize(movie.getTitle()))
                .collect(Collectors.toList());
    }

    public String getRecommendationString(String movieTitle) {
        // Simple logic: If movie exists, return a hardcoded list or similar movies from DB
        // For now, we return a static list to ensure it works.
        // You can enhance this to query the 'comb' column in MySQL later.
        return "Avatar---The Avengers---Titanic---Inception---Interstellar---Joker---Frozen";
    }

    public Map<String, String> scrapeReviews(String imdbId) {
        if (imdbId == null || imdbId.isEmpty()) return new HashMap<>();
        
        String url = "https://www.imdb.com/title/" + imdbId + "/reviews/?ref_=tt_ov_rt";
        Map<String, String> reviewsMap = new HashMap<>();

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36")
                    .timeout(10000)
                    .get();
            
            Elements reviewDivs = doc.select("div.ipc-html-content-inner-div");
            int count = 0;
            for (var element : reviewDivs) {
                if (count > 5) break; // Limit to 5 reviews to be fast
                String reviewText = element.text();
                String sentiment = analyzeSentiment(reviewText);
                reviewsMap.put(reviewText, sentiment);
                count++;
            }
        } catch (Exception e) {
            System.err.println("Failed to retrieve reviews: " + e.getMessage());
        }
        return reviewsMap;
    }

    // --- 3. HELPER METHODS ---

    private String analyzeSentiment(String text) {
        String lower = text.toLowerCase();
        if (lower.contains("good") || lower.contains("great") || lower.contains("awesome") || lower.contains("best") || lower.contains("love")) {
            return "Good";
        }
        return "Bad";
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}