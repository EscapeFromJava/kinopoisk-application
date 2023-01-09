package com.example.kinopoiskapplication.service;

import com.example.kinopoiskapplication.model.dto.DirectorDto;
import com.example.kinopoiskapplication.model.entity.Director;
import com.example.kinopoiskapplication.repository.DirectorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    @Transactional
    public Director saveDirector(DirectorDto directorDto) {
        Director director = Director.builder()
                .firstName(directorDto.getFirstName())
                .lastName(directorDto.getLastName())
                .build();

        return directorRepository.save(director);
    }

    public Optional<Director> getDirectorByName(String firstName, String lastName) {
        return directorRepository.findByFirstNameAndLastName(firstName, lastName);
    }

}
