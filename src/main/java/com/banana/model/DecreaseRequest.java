package com.banana.model;

import lombok.Getter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Getter
public class DecreaseRequest {

    @NotNull
    @Digits(integer = 6, fraction = 2)
    private Double value;

    @NotNull
    private String token;
}
