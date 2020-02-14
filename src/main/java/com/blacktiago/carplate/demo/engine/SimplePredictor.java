package com.blacktiago.carplate.demo.engine;

import com.blacktiago.carplate.demo.date.DateEvaluator;
import com.blacktiago.carplate.demo.date.SimpleDateEvaluator;
import com.blacktiago.carplate.demo.plate.PlateEvaluator;
import com.blacktiago.carplate.demo.plate.SimplePlateEvaluator;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class SimplePredictor implements Predictor{

    private DateEvaluator dateEvaluator = new SimpleDateEvaluator();
    private PlateEvaluator plateEvaluator = new SimplePlateEvaluator();

    private Date date;
    private int dayOfWeek;
    private int lastPlateDigit;

    public boolean isAllowed(String plate, String date, String time){
        boolean allowed = false;
        processDate(date);
        processPlate(plate);
        processTime(time);
        return allowed;
    }

    private void processTime(String time) {
    }

    @Override
    public String validPlateExample() {
        return "PDL 2312";
    }

    private void processPlate(String plate) {
        if(plateEvaluator.isValidPlate(plate)){
            this.lastPlateDigit = plateEvaluator.getEvaluationDigit(plate);
        } else {
            throw new IllegalArgumentException("Plate "+plate+" is not valid");
        }
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
