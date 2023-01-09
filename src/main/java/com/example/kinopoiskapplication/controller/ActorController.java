package com.example.kinopoiskapplication.controller;

import com.example.kinopoiskapplication.model.dto.ActorDto;
import com.example.kinopoiskapplication.model.entity.Actor;
import com.example.kinopoiskapplication.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    @PostMapping
    public ResponseEntity<String> saveActor(@RequestBody ActorDto actorDto) {
        try {
            actorService.saveActor(actorDto);
            return new ResponseEntity<>("Actor saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Incorrect data. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
