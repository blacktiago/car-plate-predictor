package com.blacktiago.carplate.demo.plate;


public class SimplePlateEvaluator implements PlateEvaluator{

    @Override
    public boolean isValidPlate(String plate) {


        return false;
    }

    @Override
    public int getEvaluationDigit(String plate) {

        return 2;
    }
}
