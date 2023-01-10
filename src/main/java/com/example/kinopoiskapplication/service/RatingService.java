package com.example.kinopoiskapplication.service;

import com.example.kinopoiskapplication.model.dto.RatingDto;
import com.example.kinopoiskapplication.model.entity.Movie;
import com.example.kinopoiskapplication.model.entity.Rating;
import com.example.kinopoiskapplication.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    public String addRatingToMovie(Movie movie, int value) {
        Rating rating = Rating.builder()
                .movie(movie)
                .value(value)
                .build();
        ratingRepository.save(rating);
        return "The rating of the film " + movie.getTitle() + " was increased by " + value;
    }

    public RatingDto getRatingByMovieId(Long id) {
        Optional<Rating> optionalRating = ratingRepository.getRatingById(id);
        if (optionalRating.isPresent()) {
            Rating rating = optionalRating.get();
            return RatingDto.builder()
                    .movie(rating.getMovie().getTitle())
                    .rating(rating.getValue())
                    .build();
        }
        //TODO it's bad
        return null;
    }
}
