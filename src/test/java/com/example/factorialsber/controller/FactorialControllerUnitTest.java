package com.example.factorialsber.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class FactorialControllerUnitTest {

    private final FactorialController controller = new FactorialController();

    @Test
    void whenNumberIsValid_thenReturnsFactorial() {
        ResponseEntity<?> response = controller.calculateFactorial(Map.of("factorial_num", 5));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(Map.of("result", BigInteger.valueOf(120)));
    }

    @Test
    void whenNumberIsInvalid_thenReturnsBadRequest() {
        ResponseEntity<?> response = controller.calculateFactorial(Map.of("factorial_num", -1));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo(Map.of("error", "Number must be between 0 and 100"));
    }

    @Test
    void whenNumberIsMissing_thenReturnsBadRequest() {
        ResponseEntity<?> response = controller.calculateFactorial(Map.of());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo(Map.of("error", "Number must be between 0 and 100"));
    }
}
