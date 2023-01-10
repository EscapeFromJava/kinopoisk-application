package com.example.kinopoiskapplication.repository;

import com.example.kinopoiskapplication.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("FROM Rating r JOIN FETCH r.movie m WHERE m.id = :id")
    Optional<Rating> getRatingById(Long id);

}