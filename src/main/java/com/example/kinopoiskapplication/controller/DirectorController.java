package com.example.kinopoiskapplication.controller;

import com.example.kinopoiskapplication.model.dto.DirectorDto;
import com.example.kinopoiskapplication.service.DirectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/directors")
@Slf4j
public class DirectorController {

    private final DirectorService directorService;

    @PostMapping
    public String saveDirector(@Valid @RequestBody DirectorDto directorDto) {
        directorService.saveDirector(directorDto);
        log.info("Director " + directorDto.getFirstName() + " " + directorDto.getLastName() + " saved");
        return "Director saved successfully";
    }

}
