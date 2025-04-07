package com.nicholasashenfelter.DiscreteJava.service;

import java.math.BigInteger;

public class ServiceHelper {
    // Helper function to calculate LCM using BigInteger
    static BigInteger lcm(BigInteger a, BigInteger b) {
        // Avoid overflow by dividing first
        return a.multiply(b.divide(a.gcd(b)));
    }

    static boolean isPrime(BigInteger n) {
        //TODO: Increase Certainty if necessary, or change to allow value to be passed in a body?
        return n.isProbablePrime(20);
    }
}
