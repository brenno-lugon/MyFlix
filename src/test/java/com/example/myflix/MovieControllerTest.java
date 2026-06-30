package com.example.myflix;

import com.example.myflix.domain.model.Movie;
import com.example.myflix.domain.port.in.MovieUseCase;
import com.example.myflix.infrastructure.web.controller.MovieController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieUseCase movieUseCase;

    @Test
    void shouldCreateMovie() throws Exception {
        Movie createdMovie = new Movie(
                "123",
                "Matrix",
                "A hacker discovers the truth about reality.",
                "Sci-Fi, Comedy",
                1999,
                LocalDateTime.now()
        );

        Mockito.when(movieUseCase.create(any(Movie.class)))
                .thenReturn(createdMovie);

        mockMvc.perform(post("/movies")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "Matrix",
                                  "description": "A hacker discovers the truth about reality.",
                                  "genres": "Sci-Fi, Comedy",
                                  "releaseYear": 1999
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.title").value("Matrix"))
                .andExpect(jsonPath("$.genres").value("Sci-Fi, Comedy"))
                .andExpect(jsonPath("$.releaseYear").value(1999));
    }
}

