package com.example.kinopoiskapplication.util;

import com.example.kinopoiskapplication.model.entity.Actor;
import com.example.kinopoiskapplication.model.entity.Director;
import com.example.kinopoiskapplication.model.entity.Movie;
import com.example.kinopoiskapplication.model.entity.enums.Genre;
import com.example.kinopoiskapplication.repository.ActorRepository;
import com.example.kinopoiskapplication.repository.DirectorRepository;
import com.example.kinopoiskapplication.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDataBase {

    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final MovieRepository movieRepository;

    @PostConstruct
    public void init() {
//        movies();
    }

    private void movies() {
        Actor bradPitt = Actor.builder().firstName("Brad").lastName("Pitt").build();
        Actor bruceWillis = Actor.builder().firstName("Bruce").lastName("Willis").build();
        Actor tomHanks = Actor.builder().firstName("Tom").lastName("Hanks").build();
        Actor morganFreeman = Actor.builder().firstName("Morgan").lastName("Freeman").build();
        Actor leonardoDiCaprio = Actor.builder().firstName("Leonardo").lastName("DiCaprio").build();

        Director davidFincher = Director.builder().firstName("David").lastName("Fincher").build();
        Director quentinTarantion = Director.builder().firstName("Quentin").lastName("Tarantino").build();
        Director frankDarabont = Director.builder().firstName("Frank").lastName("Darabont").build();
        Director stevenSpielberg = Director.builder().firstName("Steven").lastName("Spielberg").build();

        Movie movie1 = Movie.builder()
                .title("Fight Club")
                .description("An insurance worker destroys the routine of his prosperous life. Cult drama based on the book by Chuck Palahniuk")
                .year(1999)
                .genre(Genre.THRILLER)
                .director(davidFincher)
                .build();
        movie1.getActors().add(bradPitt);

        Movie movie2 = Movie.builder()
                .title("Pulp Fiction")
                .description("Several related stories from the life of bandits. Quentin Tarantino's Masterpiece that Changed World Cinema")
                .year(1994)
                .genre(Genre.DRAMA)
                .director(quentinTarantion)
                .build();
        movie2.getActors().add(bruceWillis);

        Movie movie3 = Movie.builder()
                .title("The Green Mile")
                .description("A prisoner with a divine gift appears on death row. Mystery drama based on the novel by Stephen King")
                .year(1999)
                .genre(Genre.DRAMA)
                .director(frankDarabont)
                .build();
        movie3.getActors().add(tomHanks);

        Movie movie4 = Movie.builder()
                .title("Inglourious Basterds")
                .description("An American special squad brutally cracks down on the Nazis. Parody of war films from Quentin Tarantino")
                .year(2009)
                .genre(Genre.ACTION)
                .director(quentinTarantion)
                .build();
        movie4.getActors().add(bradPitt);

        Movie movie5 = Movie.builder()
                .title("The Curious Case of Benjamin Button")
                .description("I was born under strange circumstances")
                .year(2008)
                .genre(Genre.FANTASY)
                .director(davidFincher)
                .build();
        movie5.getActors().add(bradPitt);

        Movie movie6 = Movie.builder()
                .title("Se7en")
                .description("He imagined himself to be God and began to punish")
                .year(1995)
                .genre(Genre.THRILLER)
                .director(davidFincher)
                .build();
        movie6.getActors().add(bradPitt);
        movie6.getActors().add(morganFreeman);

        Movie movie7 = Movie.builder()
                .title("Catch Me If You Can")
                .description("A virtuoso swindler has been leading the FBI by the nose for years. Steven Spielberg's hit based on real events with Leonardo DiCaprio")
                .year(2002)
                .genre(Genre.CRIMINAL)
                .director(stevenSpielberg)
                .build();
        movie7.getActors().add(tomHanks);
        movie7.getActors().add(leonardoDiCaprio);

        movieRepository.saveAll(List.of(movie1, movie2, movie3, movie4, movie5, movie6, movie7));
    }
}
