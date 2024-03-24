package com.example.factorialsber.integration;

import com.example.factorialsber.dto.FactorialRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FactorialIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void calculateFactorialSuccess() {
        FactorialRequestDto requestDto = new FactorialRequestDto();
        requestDto.setFactorialNum(5);

        ResponseEntity<String> response = restTemplate.postForEntity("/factorial", new HttpEntity<>(requestDto), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void calculateFactorialBadRequest() {
        FactorialRequestDto requestDto = new FactorialRequestDto();

        requestDto.setFactorialNum(101);

        ResponseEntity<String> response = restTemplate.postForEntity("/factorial", new HttpEntity<>(requestDto), String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
