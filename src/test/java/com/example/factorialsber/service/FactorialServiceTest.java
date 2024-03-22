package com.example.factorialsber.service;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorialServiceTest {

    private final FactorialService factorialService = new FactorialService();

    @Test
    void calculateFactorialForZero() {
        assertEquals(BigInteger.ONE, factorialService.calculateFactorial(0));
    }

    @Test
    void calculateFactorialForPositiveNumber() {
        assertEquals(BigInteger.valueOf(120), factorialService.calculateFactorial(5));
    }
}