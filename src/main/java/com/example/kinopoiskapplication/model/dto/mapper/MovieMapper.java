package com.example.kinopoiskapplication.model.dto.mapper;

import com.example.kinopoiskapplication.model.dto.ActorDto;
import com.example.kinopoiskapplication.model.dto.MovieDto;
import com.example.kinopoiskapplication.model.entity.Movie;

import java.util.stream.Collectors;

public class MovieMapper {
    public static MovieDto movieToMovieDto(Movie movie) {
        return MovieDto.builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .genre(movie.getGenre().name())
                .director(movie.getDirector().getFirstName() + " " + movie.getDirector().getLastName())
                .year(movie.getYear())
                .actors(movie.getActors().stream().map(
                                a -> ActorDto.builder()
                                        .firstName(a.getFirstName())
                                        .lastName(a.getLastName())
                                        .birthday(a.getBirthday().toString())
                                        .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
