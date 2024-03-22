package com.example.factorialsber.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FactorialIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void calculateFactorialReturnsCorrectResult() {
        ResponseEntity<Map> response = restTemplate.postForEntity("http://localhost:" + port + "/factorial", Map.of("factorial_num", "5"), Map.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Map.of("result", 120), response.getBody());
    }

    @Test
    public void calculateFactorialBadRequestWhenNumberIsInvalid() {
        ResponseEntity<Map> response = restTemplate.postForEntity("http://localhost:" + port + "/factorial", Map.of("factorial_num", "abc"), Map.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The value of 'factorial_num' must be a valid number", response.getBody().get("error"));
    }
}