package com.blacktiago.carplate.demo.plate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimplePlateEvaluator implements PlateEvaluator{

    private Pattern pattern = Pattern.compile("[A-Za-z]{3} [0-9]{3,4}");

    @Override
    public boolean isValidPlate(String plate) {

        plate = plate.trim();
        Matcher matcher = pattern.matcher(plate);

        return plate.length() > 5 && plate.length() < 9 && matcher.matches();
    }

    @Override
    public int getEvaluationDigit(String plate) {

        return Integer.parseInt(plate.substring(plate.length() - 1));
    }

    @Override
    public String validPlateExample() {
        return "PDL 2312";
    }
}
