package com.example.kinopoiskapplication.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingDto {
    private String movie;
    private int rating;
}
