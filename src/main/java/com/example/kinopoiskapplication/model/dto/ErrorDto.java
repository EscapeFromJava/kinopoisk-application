package com.example.kinopoiskapplication.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDto {

    private LocalDateTime date;
    private int status;
    private String error;

}
