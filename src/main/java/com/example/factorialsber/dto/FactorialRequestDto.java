package com.example.factorialsber.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class FactorialRequestDto {

    @NotNull(message = "The 'factorial_num' must not be null")
    @Min(value = 0, message = "Number must be greater than or equal to 0")
    @Max(value = 100, message = "Number must be less than or equal to 100")
    private Integer factorialNum;

    public void setFactorialNum(String factorialNumStr) {
        try {
            Integer value = Integer.valueOf(factorialNumStr);
            this.factorialNum = value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format");
        }
    }

    public Integer getFactorialNum() {
        return factorialNum;
    }
}
