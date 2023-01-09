package com.example.kinopoiskapplication.service;

import com.example.kinopoiskapplication.model.dto.ActorDto;
import com.example.kinopoiskapplication.model.dto.DirectorDto;
import com.example.kinopoiskapplication.model.dto.MovieDto;
import com.example.kinopoiskapplication.model.dto.mapper.MovieMapper;
import com.example.kinopoiskapplication.model.entity.Actor;
import com.example.kinopoiskapplication.model.entity.Director;
import com.example.kinopoiskapplication.model.entity.Movie;
import com.example.kinopoiskapplication.model.entity.enums.Genre;
import com.example.kinopoiskapplication.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final DirectorService directorService;
    private final ActorService actorService;

    public List<MovieDto> getMovies() {
        return movieRepository.getMovies().stream()
                .map(MovieMapper::movieToMovieDto)
                .toList();
    }

    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).get();
        return MovieMapper.movieToMovieDto(movie);
    }

    @Transactional
    public void saveMovie(MovieDto movieDto) {
        String[] dArray = movieDto.getDirector().split(" ");
        Director director = directorService.getDirectorByName(dArray[0], dArray[1]).orElse(
                directorService.saveDirector(
                        DirectorDto.builder().firstName(dArray[0]).lastName(dArray[1]).build())
        );

        List<Actor> actorList = new ArrayList<>();
        for (ActorDto actor : movieDto.getActors()) {
            Optional<Actor> actorByName = actorService.getActorByName(actor.getFirstName(), actor.getLastName());
            if (actorByName.isPresent()) {
                actorList.add(actorByName.get());
            } else {
                actorService.saveActor(
                        ActorDto.builder()
                                .firstName(actor.getFirstName())
                                .lastName(actor.getLastName())
                                .build());
            }

            Movie movie = Movie.builder()
                    .title(movieDto.getTitle())
                    .description(movieDto.getDescription())
                    .genre(Genre.valueOf(movieDto.getGenre().toUpperCase()))
                    .director(director)
                    .year(movieDto.getYear())
                    .actors(actorList)
                    .build();

            movieRepository.save(movie);
        }
    }

    public MovieDto getMovieByTitle(String title) {
        Movie movie = movieRepository.getMovieByTitle(title).get();
        return MovieMapper.movieToMovieDto(movie);
    }
}
