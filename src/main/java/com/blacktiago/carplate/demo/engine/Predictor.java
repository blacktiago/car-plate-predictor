package com.blacktiago.carplate.demo.engine;

public interface Predictor {

    boolean isAllowed(String plate, String date, String time);

    Prediction canDrive(String plate, String date, String time);
}
