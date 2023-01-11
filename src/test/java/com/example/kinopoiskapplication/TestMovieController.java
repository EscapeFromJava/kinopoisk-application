package com.example.kinopoiskapplication;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TestMovieController extends AbstractControllerTest {

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"/script/TestMovieController/getMovieByTitle/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = {"/script/TestMovieController/getMovieByTitle/After.sql"})
    void getMovieByTitle() throws Exception {
        mockMvc.perform(
                        get("/api/movies/filter/{title}", "Title")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", Is.is("Title")))
                .andExpect(jsonPath("description", Is.is("Description")))
                .andExpect(jsonPath("genre", Is.is("ROM_COM")))
                .andExpect(jsonPath("year", Is.is(2000)))
                .andExpect(jsonPath("director", Is.is("FirstName LastName")))
                .andExpect(jsonPath("actors.length()", Is.is(3)))
                .andExpect(jsonPath("actors[0]", Is.is("ActorFirstName 1 ActorLastName 1")))
                .andExpect(jsonPath("actors[1]", Is.is("ActorFirstName 2 ActorLastName 2")))
                .andExpect(jsonPath("actors[2]", Is.is("ActorFirstName 3 ActorLastName 3")));

    }
}
