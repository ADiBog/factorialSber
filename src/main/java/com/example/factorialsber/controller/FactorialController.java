package com.example.factorialsber.controller;

import com.example.factorialsber.dto.FactorialRequestDto;
import com.example.factorialsber.dto.FactorialResponseDto;
import com.example.factorialsber.service.FactorialService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class FactorialController {

    private final FactorialService factorialService;

    public FactorialController(FactorialService factorialService) {
        this.factorialService = factorialService;
    }

    @PostMapping("/factorial")
    public ResponseEntity<FactorialResponseDto> calculateFactorial(@Validated @RequestBody FactorialRequestDto requestDto) {
        BigInteger result = factorialService.calculateFactorial(requestDto.getFactorialNum());
        FactorialResponseDto responseDto = new FactorialResponseDto(result);
        return ResponseEntity.ok(responseDto);
    }
}
