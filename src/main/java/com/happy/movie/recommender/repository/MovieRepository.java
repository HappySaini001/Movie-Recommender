package com.happy.movie.recommender.repository;

import com.happy.movie.recommender.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// @Repository tells Spring this is a database manager
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    /*
     * MAGIC METHOD:
     * We don't write the SQL. We just name the method correctly, and Spring guesses the SQL.
     *
     * "findBy" -> SELECT * FROM
     * "Title"  -> WHERE title
     * "Containing" -> LIKE %keyword%
     * "IgnoreCase" -> (case insensitive)
     *
     * Resulting SQL: SELECT * FROM movies WHERE LOWER(title) LIKE LOWER('%keyword%')
     */
    List<Movie> findByTitleContainingIgnoreCase(String keyword);

}