package com.nicholasashenfelter.DiscreteJava.controller;

import com.nicholasashenfelter.DiscreteJava.ValidationHandler.ValidationHandler;
import com.nicholasashenfelter.DiscreteJava.model.CalculationRequest;
import com.nicholasashenfelter.DiscreteJava.model.FibonacciRequest;
import com.nicholasashenfelter.DiscreteJava.service.FibonacciSumService;
import com.nicholasashenfelter.DiscreteJava.service.GreatestPrimeFactorService;
import com.nicholasashenfelter.DiscreteJava.service.SummationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController("discreteController")
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class DiscreteJavaController {

    private final ValidationHandler validationHandler;

    private final SummationService summationService;
    private final FibonacciSumService fibonacciSumService;
    private final GreatestPrimeFactorService greatestPrimeFactorService;

    @PostMapping("/calculate")
    @ResponseBody
    public ResponseEntity<BigInteger> calculate(@RequestBody CalculationRequest request) {
        validationHandler.validateCalculationRequest(request);
        BigInteger response = summationService.inclusionExclusionSum(request.getRange(), request.getDivisors());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Adds all numbers within the fibonacci sequence given
    // A limit (the max fibonacci value, not sequence position
    // A sum type, either even, odd, or if both passed then the total of all values
    // Inclusivity, whether the limit should be added to the sum or not, only applies to edge cases where limit falls on a sequence number.
    @PostMapping({"/fibonacci", "/fibonacciSums"})
    @ResponseBody
    public ResponseEntity<BigInteger> fibonacciSums(@RequestBody FibonacciRequest request){
        validationHandler.validateFibonacciSumRequest(request);
        BigInteger response = fibonacciSumService.fibonacciSums(request.getFibLimit(), request.getSumType(), request.getIsInclusive());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping({"/greatestPrimeFactor/{toFactor}"})
    @ResponseBody
    public ResponseEntity<BigInteger> greatestPrimeFactor(@PathVariable BigInteger toFactor){
        BigInteger response = greatestPrimeFactorService.largestPrimeFactor(toFactor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public String healthCheck(){
        return "Health Check Successful";
    }
}
