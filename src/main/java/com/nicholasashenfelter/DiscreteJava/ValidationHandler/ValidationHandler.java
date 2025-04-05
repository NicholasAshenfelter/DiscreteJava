package com.nicholasashenfelter.DiscreteJava.ValidationHandler;

import com.nicholasashenfelter.DiscreteJava.model.CalculationRequest;

import com.nicholasashenfelter.DiscreteJava.model.FibonacciRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Component
@Slf4j
public class ValidationHandler {
    private final Validator validator;

    @Autowired  // Explicitly inject the Validator
    public ValidationHandler(Validator validator) {
        this.validator = validator;
    }

    public void validateCalculationRequest(CalculationRequest calculationRequest){
        Set<ConstraintViolation<CalculationRequest>> violations = validator.validate(calculationRequest);
        if(!CollectionUtils.isEmpty(violations)){
            for(ConstraintViolation violation: violations){
                throw new ViolationException(violation.getMessage());
            }
        }
    }

    public void validateFibonacciSumRequest(FibonacciRequest fibonacciRequest){
        Set<ConstraintViolation<FibonacciRequest>> violations = validator.validate(fibonacciRequest);
        if(!CollectionUtils.isEmpty(violations)){
            for(ConstraintViolation violation: violations){
                throw new ViolationException(violation.getMessage());
            }
        }
    }
}
