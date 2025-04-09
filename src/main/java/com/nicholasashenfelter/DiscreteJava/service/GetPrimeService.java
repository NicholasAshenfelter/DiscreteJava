package com.nicholasashenfelter.DiscreteJava.service;

import jakarta.validation.constraints.AssertTrue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class GetPrimeService {
    private static final int LIMIT = 2000000;
    private static final int[] primes = generatePrimes(LIMIT);

    private static int[] generatePrimes(int limit) {
        boolean[] isPrime = new boolean[limit];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i < limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primeList = new ArrayList<>();
        for (int i = 2; i < limit && primeList.size() < 2000000; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }

        return primeList.stream().mapToInt(i -> i).toArray();
    }

    public static int getPrimes(int n){
        return primes[n];
    }
}
