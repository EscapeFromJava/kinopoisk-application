package com.example.kinopoiskapplication.controller;

import com.example.kinopoiskapplication.model.dto.MovieDto;
import com.example.kinopoiskapplication.model.dto.RatingDto;
import com.example.kinopoiskapplication.model.entity.Movie;
import com.example.kinopoiskapplication.service.MovieService;
import com.example.kinopoiskapplication.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
@Slf4j
public class MovieController {
    private final MovieService movieService;
    private final RatingService ratingService;

    @GetMapping
    public List<MovieDto> getMovies() {
        log.info("Use api 'getMovies'");
        return movieService.getMovies();
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable Long id) {
        MovieDto movie = movieService.getMovieDtoById(id);
        movie.add(linkTo(methodOn(MovieController.class).getRatingByMovieId(id)).withSelfRel());
        log.info("Save movie: " + movie);
        return movie;
    }

    @GetMapping("/filter/{title}")
    public ResponseEntity<?> getMovieByTitle(@PathVariable String title) {
        try {
            MovieDto movieByTitle = movieService.getMovieByTitle(title);
            return new ResponseEntity<>(movieByTitle, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Not unique result. " + e.getMessage());
            return new ResponseEntity<>("Not unique result. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public String saveMovie(@RequestBody MovieDto movieDto) {
        movieService.saveMovie(movieDto);
        return "Movie saved successfully";
    }

    @PostMapping("/{id}/rating/{value}")
    public String addMovieRating(@PathVariable Long id, @PathVariable int value) {
        Movie movie = movieService.getMovieById(id);
        return ratingService.addRatingToMovie(movie, value);
    }

    @GetMapping("/{id}/rating")
    public ResponseEntity<?> getRatingByMovieId(@PathVariable Long id) {
        RatingDto ratingByMovieId = ratingService.getRatingByMovieId(id);
        if (ratingByMovieId == null) {
            return new ResponseEntity<>("Movie with id " + id + " has not rating", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ratingByMovieId, HttpStatus.OK);
    }
}
