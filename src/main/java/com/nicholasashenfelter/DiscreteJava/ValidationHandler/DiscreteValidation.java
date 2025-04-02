package com.nicholasashenfelter.DiscreteJava.ValidationHandler;

public class DiscreteValidation {

    private DiscreteValidation(){

    }

    public interface Missing{};
    public interface Empty{};
    public interface Invalid{};

    public static final String MISSING = "Requested Header Missing: ";
    public static final String EMPTY = "Requested Header Empty: ";
    public static final String INVALID = "Requested Header Invalid: ";
}
