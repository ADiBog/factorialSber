package com.example.factorialsber.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Map;

@RestController
public class FactorialController {

    @PostMapping("/factorial")
    public ResponseEntity<?> calculateFactorial(@RequestBody Map<String, Integer> request) {
        int number = request.getOrDefault("factorial_num", -1);
        if(number < 0 || number > 100) {
            return ResponseEntity.badRequest().body(Map.of("error", "Number must be between 0 and 100"));
        }
        BigInteger result = factorial(number);
        return ResponseEntity.ok(Map.of("result", result));
    }

    private BigInteger factorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
