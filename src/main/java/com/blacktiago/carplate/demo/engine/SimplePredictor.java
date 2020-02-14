package com.blacktiago.carplate.demo.engine;

import com.blacktiago.carplate.demo.date.DateEvaluator;
import com.blacktiago.carplate.demo.date.SimpleDateEvaluator;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class SimplePredictor implements Predictor{

    private DateEvaluator dateEvaluator = new SimpleDateEvaluator();

    private Date date;
    private int dayOfWeek;

    public boolean isAllowed(String plate, String date, String time){
        boolean allowed = false;
        processDate(date);
        return allowed;
    }

    private void processDate(String date) {
        try{
            if(dateEvaluator.isValidFormat(date)){
                this.date = dateEvaluator.parse(date);
                Calendar calendar = Calendar.getInstance();
                this.dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            }
        } catch (Exception ex){
            log.error("Enexpected date parsing error ", ex);
        }
    }
}
