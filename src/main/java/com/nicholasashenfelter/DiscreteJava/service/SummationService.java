package com.nicholasashenfelter.DiscreteJava.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

import static com.nicholasashenfelter.DiscreteJava.service.ServiceHelper.lcm;

@Service
@Slf4j
public class SummationService {

    public BigInteger inclusionExclusionSum(BigInteger n, List<BigInteger> divisors) {
        //log.info("Begin Summation Process");
        BigInteger totalSum = BigInteger.ZERO;
        int len = divisors.size();

        for (int i = 1; i < (1 << len); i++) {
            BigInteger lcmVal = BigInteger.ONE;
            int bits = Integer.bitCount(i);

            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) != 0) {
                    lcmVal = lcm(lcmVal, divisors.get(j));
                }
            }

            BigInteger count = n.divide(lcmVal);
            BigInteger subsetSum = lcmVal.multiply(count.multiply(count.add(BigInteger.ONE))).divide(BigInteger.TWO);

            if (bits % 2 == 1) {
                totalSum = totalSum.add(subsetSum);
            } else {
                totalSum = totalSum.subtract(subsetSum);
            }
        }

        return totalSum;
    }


}
