package com.nicholasashenfelter.DiscreteJava.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Slf4j
public class FibonacciSumService {
    public BigInteger fibonacciSums(BigInteger n, List<String> types, boolean inclusive){
        if(types.contains("ODD") && types.contains("EVEN")){
            return fibonacciOdd(n, inclusive).add(fibonacciEven(n, inclusive));
        } else if (types.contains("ODD")){
            return fibonacciOdd(n, inclusive);
        }
        return fibonacciEven(n, inclusive);
    }

    private BigInteger fibonacciEven(BigInteger n, boolean inclusive){
        BigInteger i = BigInteger.valueOf(2), j = BigInteger.valueOf(8), sum = BigInteger.ZERO;
        if(inclusive){
            while(i.compareTo(n) <= 0){
                sum = sum.add(i);
                BigInteger k = BigInteger.valueOf(4).multiply(j).add(i);
                i = j;
                j = k;
            }
        } else {
            while(i.compareTo(n) < 0){
                sum = sum.add(i);
                BigInteger k = BigInteger.valueOf(4).multiply(j).add(i);
                i = j;
                j = k;
            }
        }
        return sum;
    }

    private BigInteger fibonacciOdd(BigInteger n, boolean inclusive){
        BigInteger i = BigInteger.valueOf(1), j = BigInteger.valueOf(1), sum = BigInteger.ZERO;
        if(inclusive){
            while (i.compareTo(n) <= 0) {
                if (i.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                    sum = sum.add(i);
                }
                BigInteger k = i.add(j);
                i = j;
                j = k;
            }
        } else {
            while (i.compareTo(n) < 0) {
                if (i.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                    sum = sum.add(i);
                }
                BigInteger k = i.add(j);
                i = j;
                j = k;
            }
        }
        return sum;
    }
}
