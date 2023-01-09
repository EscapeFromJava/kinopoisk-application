package com.example.kinopoiskapplication.repository;

import com.example.kinopoiskapplication.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("FROM Movie m JOIN FETCH m.actors a JOIN FETCH m.director d")
    List<Movie> getMovies();
    @Query("FROM Movie m JOIN FETCH m.actors a JOIN FETCH m.director d WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Optional<Movie> getMovieByTitle(String title);

}