package com.example.kinopoiskapplication.service;

import com.example.kinopoiskapplication.model.dto.ActorDto;
import com.example.kinopoiskapplication.model.entity.Actor;
import com.example.kinopoiskapplication.repository.ActorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    @Transactional
    public void saveActor(ActorDto actorDto) {
        Actor actor = Actor.builder()
                .firstName(actorDto.getFirstName())
                .lastName(actorDto.getLastName())
                .birthday(LocalDate.parse(actorDto.getBirthday()))
                .build();

        actorRepository.save(actor);
    }

    public Optional<Actor> getActorByName(String firstName, String lastName) {
        return actorRepository.findByFirstNameAndLastName(firstName, lastName);
    }

}
