package com.example.kinopoiskapplication.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ActorDto implements Serializable {
    private String firstName;
    private String lastName;
    private String birthday;
}