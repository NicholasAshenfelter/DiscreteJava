package com.nicholasashenfelter.DiscreteJava.controller;

import com.nicholasashenfelter.DiscreteJava.ValidationHandler.ValidationHandler;
import com.nicholasashenfelter.DiscreteJava.model.CalculationRequest;
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

    @GetMapping("/")
    public String healthCheck(){
        return "Health Check Successful";
    }
}
