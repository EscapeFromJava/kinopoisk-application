package com.example.kinopoiskapplication.exception;

import com.example.kinopoiskapplication.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e, WebRequest request) {
        Map<String, String> map = new HashMap<>();
        BindingResult result = e.getBindingResult();
        result.getAllErrors().forEach(error -> {
            String field = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
            String message = error.getDefaultMessage();
            map.put(field, message);
        });
        ErrorDto error = ErrorDto.builder()
                .date(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(map.toString())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
