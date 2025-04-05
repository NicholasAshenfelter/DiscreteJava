package com.nicholasashenfelter.DiscreteJava.controller;

import com.nicholasashenfelter.DiscreteJava.ValidationHandler.ValidationHandler;
import com.nicholasashenfelter.DiscreteJava.model.CalculationRequest;
import com.nicholasashenfelter.DiscreteJava.model.FibonacciRequest;
import com.nicholasashenfelter.DiscreteJava.service.SummationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController("discreteController")
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class DiscreteJavaController {

    private final ValidationHandler validationHandler;
    private final SummationService summationService;

    @PostMapping("/calculate")
    @ResponseBody
    public BigInteger calculate(@RequestBody CalculationRequest request) {
        validationHandler.validateCalculationRequest(request);
        //TODO: Wrap this in a response object to pass message along
        return summationService.inclusionExclusionSum(request.getRange(), request.getDivisors());
    }

    // Adds all numbers within the fibonacci sequence given
    // A limit (the max fibonacci value, not sequence position
    // A sum type, either even, odd, or if both passed then the total of all values
    // Inclusivity, whether the limit should be added to the sum or not, only applies to edge cases where limit falls on a sequence number.
    @PostMapping({"/fibonacci", "/fibonacciSums"})
    @ResponseBody
    public BigInteger fibonacciSums(@RequestBody FibonacciRequest request){
        validationHandler.validateFibonacciSumRequest(request);
        return summationService.fibonacciSums(request.getFibLimit(), request.getSumType(), request.getIsInclusive());
    }

    @GetMapping("/")
    public String healthCheck(){
        return "Health Check Successful";
    }
}
