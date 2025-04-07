package com.nicholasashenfelter.DiscreteJava.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Random;

import static com.nicholasashenfelter.DiscreteJava.service.ServiceHelper.isPrime;

@Service
@Slf4j
public class GreatestPrimeFactorService {
    private static final Random rand = new Random();

    // Pollard's Rho Factorization
    private BigInteger pollardsRho(BigInteger n) {
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return BigInteger.TWO;
        }

        BigInteger x = new BigInteger(n.bitLength(), rand);
        BigInteger y = x;
        BigInteger c = new BigInteger(n.bitLength(), rand);
        BigInteger d = BigInteger.ONE;

        while (d.equals(BigInteger.ONE)) {
            x = x.multiply(x).mod(n).add(c).mod(n);
            y = y.multiply(y).mod(n).add(c).mod(n);
            y = y.multiply(y).mod(n).add(c).mod(n);
            d = x.subtract(y).abs().gcd(n);

            if (d.equals(n)) {
                // Retry with different random values
                return pollardsRho(n);
            }
        }
        return d;
    }

    // Recursive method to get the largest prime factor
    public BigInteger largestPrimeFactor(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) return BigInteger.ONE;

        if (isPrime(n)) return n;

        BigInteger factor = pollardsRho(n);
        BigInteger coFactor = n.divide(factor);

        BigInteger max1 = largestPrimeFactor(factor);
        BigInteger max2 = largestPrimeFactor(coFactor);

        return max1.max(max2);
    }
}
