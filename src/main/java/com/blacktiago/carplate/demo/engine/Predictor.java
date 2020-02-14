package com.blacktiago.carplate.demo.engine;

public interface Predictor {

    boolean isAllowed(String plate, String date, String time);

    public String plateExample();

    public String dateExample();

    public String timeExample();
}
