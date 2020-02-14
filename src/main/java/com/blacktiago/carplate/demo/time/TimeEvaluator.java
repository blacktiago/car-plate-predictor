package com.blacktiago.carplate.demo.time;

public interface TimeEvaluator {

    boolean isValidTime(String time);

    String validTimeExample();

    int hourOf(String time);

    int minuteOf(String time);
}
