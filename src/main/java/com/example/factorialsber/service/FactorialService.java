package com.example.factorialsber.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class FactorialService {

    public BigInteger calculateFactorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
