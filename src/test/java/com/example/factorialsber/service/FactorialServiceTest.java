package com.example.factorialsber.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class FactorialServiceTest {

    private final FactorialService factorialService = new FactorialService();

    @Test
    void calculateFactorialForZero() {
        BigInteger result = factorialService.calculateFactorial(0);
        Assertions.assertEquals(BigInteger.ONE, result);
    }

    @Test
    void calculateFactorialForPositiveNumber() {
        BigInteger result = factorialService.calculateFactorial(5);
        Assertions.assertEquals(BigInteger.valueOf(120), result); // 5! = 120
    }

    @Test
    void calculateFactorialForLargeNumber() {
        BigInteger expected = new BigInteger("265252859812191058636308480000000");
        BigInteger result = factorialService.calculateFactorial(30);
        Assertions.assertEquals(expected, result);
    }

}
