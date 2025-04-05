package com.nicholasashenfelter.DiscreteJava.model;

import com.nicholasashenfelter.DiscreteJava.ValidationHandler.DiscreteValidation;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@GroupSequence({
        DiscreteValidation.Missing.class,
        DiscreteValidation.Empty.class,
        DiscreteValidation.Invalid.class,
        FibonacciRequest.class
})
public class FibonacciRequest {
    @NotNull(message = DiscreteValidation.MISSING + "Range", groups = {DiscreteValidation.Missing.class})
    BigInteger range;
    //Change to enum for ODD, EVEN (if both are passed then sum total for fib)
    @NotNull(message = DiscreteValidation.MISSING + "SumType", groups = {DiscreteValidation.Missing.class})
    @NotEmpty(message = DiscreteValidation.EMPTY + "SumType", groups = {DiscreteValidation.Empty.class})
    List<String> sumType;
    boolean inclusive;
}
