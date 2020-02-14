package com.blacktiago.carplate.demo.date;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class SimpleDateEvaluator implements DateEvaluator {

    public Date parse(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern(allowedFormat());
        dateFormat.setLenient(false);
        return dateFormat.parse(date);
    }

    public boolean isValidFormat(String date){
        boolean isValid = false;
        try{
            parse(date);
            isValid = true;
        } catch (Exception e){
            log.info("Unable to parse " + date + " please use format "+ allowedFormat());
        }
        return isValid;
    }

    public String allowedFormat(){
        return "dd-MM-yyyy";
    }
}
