package com.blacktiago.carplate.demo.time;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTimeEvaluator implements TimeEvaluator{

    private Pattern pattern = Pattern.compile("[0-9]{2}:[0-9]{2}");

    @Override
    public boolean isValidTime(String time) {
        boolean isValid = false;
        if(Optional.ofNullable(time).isPresent()){
            time = time.trim();
            Matcher matcher = pattern.matcher(time);

            isValid = matcher.matches();
        }

        if(isValid){
            String[] timeParts = time.split(":");
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);
            isValid = (hour >= 0 && hour <= 23) && (minute >= 0 && minute <= 59);
        }
        return isValid;
    }

}
