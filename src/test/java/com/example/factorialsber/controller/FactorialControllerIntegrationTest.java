package com.example.factorialsber.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FactorialController.class)
public class FactorialControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenPostFactorialWithValidNumber_thenReturnsFactorial() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"factorial_num\": 5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("120"));
    }

    @Test
    void whenPostFactorialWithInvalidNumber_thenReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"factorial_num\": -1}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Number must be between 0 and 100"));
    }

    @Test
    void whenPostFactorialWithoutNumber_thenReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Number must be between 0 and 100"));
    }
}
