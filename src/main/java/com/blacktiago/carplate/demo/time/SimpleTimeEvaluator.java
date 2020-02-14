package com.blacktiago.carplate.demo.time;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTimeEvaluator implements TimeEvaluator{

    private Pattern pattern = Pattern.compile("[0-9]{1,2}:[0-9]{2}");

    @Override
    public boolean isValidTime(String time) {
        boolean isValid = false;
        if(Optional.ofNullable(time).isPresent()){
            time = time.trim();
            Matcher matcher = pattern.matcher(time);

            isValid = matcher.matches();
        }

        if(isValid){
            int hour = hourOf(time);
            int minute = minuteOf(time);
            isValid = (hour >= 0 && hour <= 23) && (minute >= 0 && minute <= 59);
        }
        return isValid;
    }

    @Override
    public String validTimeExample() {
        return "14:30";
    }

    @Override
    public int hourOf(String time) {
        String[] timeParts = time.split(":");
        return Integer.parseInt(timeParts[0]);
    }

    @Override
    public int minuteOf(String time) {
        String[] timeParts = time.split(":");
        return Integer.parseInt(timeParts[1]);
    }

}
