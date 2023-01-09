package com.example.kinopoiskapplication;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@RequiredArgsConstructor
class TestMovieController {

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestMovieController/getMovieByTitle/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestMovieController/getMovieByTitle/After.sql"})
    void getMovieByTitle() {
        when()
                .get("/api/movies/filter/{title}", "Title")
                .then()
                .statusCode(200).
                body("title", equalTo("Title"),
                        "description", equalTo("Description"),
                        "genre", equalTo("ROM_COM"),
                        "year", equalTo(2000),
                        "actors", hasSize(3)
                );
    }
}
