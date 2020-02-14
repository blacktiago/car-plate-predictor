package com.blacktiago.carplate.demo.plate;

public interface PlateEvaluator {

    boolean isValidPlate(String plate);

    int getEvaluationDigit(String plate);

    String validPlateExample();
}