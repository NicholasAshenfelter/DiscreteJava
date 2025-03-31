package com.nicholasashenfelter.DiscreteJava.controller;

import com.nicholasashenfelter.DiscreteJava.model.CalculationRequest;
import com.nicholasashenfelter.DiscreteJava.service.SummationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DiscreteJavaController {

    @PostMapping("/calculate")
    public long calculate(@RequestBody CalculationRequest request) {
        SummationService summationService = new SummationService();
        return summationService.inclusionExclusionSum(request.getN(), request.getDivisors());
    }

    @GetMapping("/")
    public String healthCheck(){
        return "Health Check Successful";
    }
}
