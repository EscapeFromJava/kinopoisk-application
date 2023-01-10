package com.example.kinopoiskapplication.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class MovieDto extends RepresentationModel<MovieDto> implements Serializable {
    private String title;
    private String description;
    private String genre;
    private int year;
    private String director;
    private List<String> actors;
}