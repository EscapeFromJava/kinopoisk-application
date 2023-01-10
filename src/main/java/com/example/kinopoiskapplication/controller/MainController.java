package com.example.kinopoiskapplication.controller;

import com.example.kinopoiskapplication.model.dto.MovieDto;
import com.example.kinopoiskapplication.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public String getMovies(Model model) {
        List<MovieDto> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/movies/{title}")
    public String getMovieById(@PathVariable String title, Model model) {
        MovieDto movie = movieService.getMovieByTitle(title);
        model.addAttribute("movie", movie);
        return "movie";
    }

}
