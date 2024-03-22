package com.example.factorialsber.controller;

import com.example.factorialsber.service.FactorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Map;

@RestController
public class FactorialController {

    private final FactorialService factorialService;

    public FactorialController(FactorialService factorialService) {
        this.factorialService = factorialService;
    }

    @PostMapping("/factorial")
    public ResponseEntity<?> calculateFactorial(@RequestBody Map<String, Object> request) {
        if (!request.containsKey("factorial_num")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing 'factorial_num'"));
        }

        Object value = request.get("factorial_num");

        if (value == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "The 'factorial_num' is null"));
        }

        String valueStr = value.toString();

        if (!valueStr.matches("-?\\d+")) {
            return ResponseEntity.badRequest().body(Map.of("error", "The value of 'factorial_num' must be a valid number"));
        }

        int number;
        try {
            number = Integer.parseInt(valueStr);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "The value of 'factorial_num' is not a valid integer"));
        }

        if (number < 0 || number > 100) {
            return ResponseEntity.badRequest().body(Map.of("error", "Number must be between 0 and 100"));
        }

        BigInteger result = factorialService.calculateFactorial(number);
        return ResponseEntity.ok(Map.of("result", result));
    }
}
