package com.example.kinopoiskapplication.controller;

import com.example.kinopoiskapplication.model.dto.MovieDto;
import com.example.kinopoiskapplication.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDto> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/filter/{title}")
    public ResponseEntity<?> getMovieByTitle(@PathVariable String title) {
        try {
            MovieDto movieByTitle = movieService.getMovieByTitle(title);
            return new ResponseEntity<>(movieByTitle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Not unique result. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public String saveMovie(@RequestBody MovieDto movieDto) {
        movieService.saveMovie(movieDto);
        return "Movie saved successfully";
    }
}
