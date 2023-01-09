package com.example.kinopoiskapplication.controller;

import com.example.kinopoiskapplication.model.dto.DirectorDto;
import com.example.kinopoiskapplication.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/directors")
public class DirectorController {

    private final DirectorService directorService;

    @PostMapping
    public String saveDirector(@RequestBody DirectorDto directorDto) {
        directorService.saveDirector(directorDto);
        return "Director saved successfully";
    }

}
