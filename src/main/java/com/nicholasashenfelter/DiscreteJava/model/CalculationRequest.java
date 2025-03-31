package com.nicholasashenfelter.DiscreteJava.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculationRequest {
    private int n;
    private int[] divisors;
}
