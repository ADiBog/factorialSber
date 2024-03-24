package com.example.factorialsber.controller;

import com.example.factorialsber.service.FactorialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FactorialController.class)
class FactorialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FactorialService factorialService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        when(factorialService.calculateFactorial(anyInt())).thenReturn(BigInteger.valueOf(120));
    }

    @Test
    void calculateFactorialReturnsCorrectResponse() throws Exception {
        mockMvc.perform(post("/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"factorialNum\": \"5\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Map.of("result", BigInteger.valueOf(120)))));
    }
}