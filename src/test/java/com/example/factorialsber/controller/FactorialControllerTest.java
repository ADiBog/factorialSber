package com.example.factorialsber.controller;

import com.example.factorialsber.service.FactorialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FactorialController.class)
public class FactorialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FactorialService factorialService;

    @BeforeEach
    void setUp() {
        Mockito.when(factorialService.calculateFactorial(5)).thenReturn(BigInteger.valueOf(120));
    }

    @Test
    void whenMissingFactorialNum_thenBadRequest() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Missing 'factorial_num'\"}"));
    }

    @Test
    void whenFactorialNumIsNull_thenBadRequest() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"factorial_num\":null}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"The 'factorial_num' is null\"}"));
    }

    @Test
    void whenFactorialNumIsNotValidNumber_thenBadRequest() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"factorial_num\":\"1.1\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"The value of 'factorial_num' must be a valid number\"}"));
    }

    @Test
    void whenFactorialNumIsNotValidInteger_thenBadRequest() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"factorial_num\":\"999999999999999999999999999\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"The value of 'factorial_num' is not a valid integer\"}"));
    }

    @Test
    void whenFactorialNumOutOfRange_thenBadRequest() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"factorial_num\":\"-1\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Number must be between 0 and 100\"}"));
    }

    @Test
    void whenValidFactorialNum_thenSuccess() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"factorial_num\":\"5\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"result\":120}"));

        Mockito.verify(factorialService, Mockito.times(1)).calculateFactorial(5);
    }
}
