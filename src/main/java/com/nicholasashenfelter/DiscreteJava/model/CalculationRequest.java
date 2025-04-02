package com.nicholasashenfelter.DiscreteJava.model;

import com.nicholasashenfelter.DiscreteJava.ValidationHandler.DiscreteValidation;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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
        CalculationRequest.class
})
public class CalculationRequest {
    @NotNull(message = DiscreteValidation.MISSING + "Range", groups = {DiscreteValidation.Missing.class})
    private BigInteger range;
    @NotNull(message = DiscreteValidation.MISSING + "Divisors", groups = {DiscreteValidation.Missing.class})
    @NotEmpty(message = DiscreteValidation.EMPTY + "Divisors", groups = {DiscreteValidation.Empty.class})
    private List<BigInteger> divisors;

    @AssertTrue(message = DiscreteValidation.INVALID + "Range", groups = {DiscreteValidation.Invalid.class})
    private boolean isNPositive(){
        log.info("Starting Range Positive Assertion");
        range = range.abs();
        if(range.compareTo(BigInteger.ONE) < 0){
            range = BigInteger.ONE;
        }
        return true;
    }

    @AssertTrue(message = DiscreteValidation.INVALID + "Divisors", groups = {DiscreteValidation.Invalid.class})
    private boolean isDivisorsPositive(){
        log.info("Starting Divisors Positive Assertion");
        divisors.replaceAll(n -> {
            BigInteger absValue = n.abs();

            return absValue.max(BigInteger.ONE);
        });
        return true;
    }
}
