package com.example.factorialsber.dto;

import java.math.BigInteger;

public class FactorialResponseDto {
    private BigInteger result;

    public FactorialResponseDto() {
    }

    public FactorialResponseDto(BigInteger result) {
        this.result = result;
    }

    public BigInteger getResult() {
        return result;
    }

    public void setResult(BigInteger result) {
        this.result = result;
    }
}
