package com.example.kinopoiskapplication.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
public class DirectorDto implements Serializable {
    @NotBlank(message = "First name can not be empty")
    private String firstName;
    @NotBlank(message = "Last name can not be empty")
    private String lastName;
}