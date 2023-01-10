package com.example.kinopoiskapplication.service;

import com.example.kinopoiskapplication.model.dto.MovieDto;
import com.example.kinopoiskapplication.model.dto.mapper.MovieMapper;
import com.example.kinopoiskapplication.model.entity.Actor;
import com.example.kinopoiskapplication.model.entity.Director;
import com.example.kinopoiskapplication.model.entity.Movie;
import com.example.kinopoiskapplication.model.entity.enums.Genre;
import com.example.kinopoiskapplication.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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

    public MovieDto getMovieDtoById(Long id) {
        Movie movie = movieRepository.getMovieById(id);
        return MovieMapper.movieToMovieDto(movie);
    }

    @Transactional
    public void saveMovie(MovieDto movieDto) {
        String[] dArray = movieDto.getDirector().split(" ");
        Optional<Director> optionalDirector = directorService.getDirectorByName(dArray[0], dArray[1]);
        Director director;
        if (optionalDirector.isPresent()) {
            director = optionalDirector.get();
        } else {
            director = Director.builder().firstName(dArray[0]).lastName(dArray[1]).build();
        }

        List<Actor> actorList = new ArrayList<>();
        for (String actor : movieDto.getActors()) {
            String[] aArray = actor.split(" ");
            Optional<Actor> actorByName = actorService.getActorByName(aArray[0], aArray[1]);
            if (actorByName.isPresent()) {
                actorList.add(actorByName.get());
            } else {
                actorList.add(Actor.builder()
                        .firstName(aArray[0])
                        .lastName(aArray[1])
                        .birthday(LocalDate.now())
                        .build());
            }

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

    public MovieDto getMovieByTitle(String title) {
        Movie movie = movieRepository.getMovieByTitle(title).get();
        return MovieMapper.movieToMovieDto(movie);
    }

    public Movie getMovieById(Long id) {
        return movieRepository.getMovieById(id);
    }
}
