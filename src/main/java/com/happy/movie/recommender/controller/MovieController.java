package com.happy.movie.recommender.controller;

import com.happy.movie.recommender.service.MovieService;
import com.happy.movie.recommender.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        // Equivalent to @app.route("/home")
        model.addAttribute("suggestions", movieService.getSuggestions());
        return "home";
    }

    @PostMapping("/similarity")
    @ResponseBody
    public String similarity(@RequestParam("name") String movie) {
        // Equivalent to @app.route("/similarity")
        return movieService.getRecommendationString(movie);
    }

    @PostMapping("/recommend")
    public String recommend(
            @RequestParam Map<String, String> allParams,
            Model model) {
        // Equivalent to @app.route("/recommend")

        String title = allParams.get("title");
        String imdbId = allParams.get("imdb_id");
        
        // Pass basic fields directly to model
        model.addAttribute("title", title);
        model.addAttribute("poster", allParams.get("poster"));
        model.addAttribute("overview", allParams.get("overview"));
        model.addAttribute("vote_average", allParams.get("rating"));
        model.addAttribute("vote_count", allParams.get("vote_count"));
        model.addAttribute("release_date", allParams.get("release_date"));
        model.addAttribute("runtime", allParams.get("runtime"));
        model.addAttribute("status", allParams.get("status"));
        model.addAttribute("genres", allParams.get("genres"));

        // Process Cast & Recs (Converting string representations to Maps/Lists)
        // In Java, we manually parse the list strings sent by AJAX
        Map<String, String> movieCards = parseRecs(allParams.get("rec_posters"), allParams.get("rec_movies"));
        Map<String, List<String>> casts = parseCasts(allParams);
        Map<String, List<String>> castDetails = parseCastDetails(allParams);

        model.addAttribute("movie_cards", movieCards);
        model.addAttribute("casts", casts);
        model.addAttribute("cast_details", castDetails);

        // Scrape Reviews
        Map<String, String> reviews = movieService.scrapeReviews(imdbId);
        model.addAttribute("reviews", reviews);

        return "recommend";
    }

    // Helper methods to parse the JSON-like strings sent from frontend
    private Map<String, String> parseRecs(String postersStr, String moviesStr) {
        List<String> posters = convertToList(postersStr);
        List<String> movies = convertToList(moviesStr);
        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < Math.min(posters.size(), movies.size()); i++) {
            map.put(posters.get(i), movies.get(i));
        }
        return map;
    }

    private Map<String, List<String>> parseCasts(Map<String, String> params) {
        List<String> names = convertToList(params.get("cast_names"));
        List<String> chars = convertToList(params.get("cast_chars"));
        List<String> profiles = convertToList(params.get("cast_profiles"));
        String idsStr = params.get("cast_ids"); 
        // Simple clean for IDs as per python logic
        idsStr = idsStr.replace("[", "").replace("]", "");
        List<String> ids = Arrays.asList(idsStr.split(","));

        Map<String, List<String>> map = new LinkedHashMap<>();
        for (int i = 0; i < names.size(); i++) {
            // [cast_ids[i], cast_chars[i], cast_profiles[i]]
            map.put(names.get(i), Arrays.asList(ids.get(i), chars.get(i), profiles.get(i)));
        }
        return map;
    }

    private Map<String, List<String>> parseCastDetails(Map<String, String> params) {
        // Similar parsing for cast_details...
        // For brevity, using simplified parsing logic
        List<String> names = convertToList(params.get("cast_names"));
        List<String> bios = convertToList(params.get("cast_bios"));
        List<String> places = convertToList(params.get("cast_places"));
        List<String> bdays = convertToList(params.get("cast_bdays"));
        List<String> profiles = convertToList(params.get("cast_profiles"));
        String idsStr = params.get("cast_ids").replace("[", "").replace("]", "");
        List<String> ids = Arrays.asList(idsStr.split(","));

        Map<String, List<String>> map = new LinkedHashMap<>();
        for(int i=0; i<names.size(); i++) {
             // [cast_ids[i], cast_profiles[i], cast_bdays[i], cast_places[i], cast_bios[i]]
             map.put(names.get(i), Arrays.asList(ids.get(i), profiles.get(i), bdays.get(i), places.get(i), bios.get(i)));
        }
        return map;
    }

    private List<String> convertToList(String str) {
        if(str == null) return new ArrayList<>();
        // Logic from python: my_list.split('","')
        // Remove outer brackets
        String cleaned = str.replace("[\"", "").replace("\"]", "").replace("[\"", "");
        if(cleaned.endsWith("\"]")) cleaned = cleaned.substring(0, cleaned.length()-2);
        return new ArrayList<>(Arrays.asList(cleaned.split("\",\"")));
    }
}