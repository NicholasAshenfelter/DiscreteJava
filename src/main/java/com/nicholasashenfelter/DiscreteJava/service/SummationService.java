package com.nicholasashenfelter.DiscreteJava.service;

import org.springframework.stereotype.Service;

@Service
public class SummationService {
    public long inclusionExclusionSum(int n, int[] divisors) {
        long totalSum = 0;
        int len = divisors.length;

        for (int i = 1; i < (1 << len); i++) {
            long lcmVal = 1;
            int bits = Integer.bitCount(i);

            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) != 0) {
                    lcmVal = lcm(lcmVal, divisors[j]);
                }
            }

            long count = n / lcmVal;
            long subsetSum = lcmVal * (count * (count + 1)) / 2;

            if (bits % 2 == 1) {
                totalSum += subsetSum;
            } else {
                totalSum -= subsetSum;
            }
        }

        return totalSum;
    }

    private long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
