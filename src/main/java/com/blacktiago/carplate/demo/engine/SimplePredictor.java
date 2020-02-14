package com.blacktiago.carplate.demo.engine;

import com.blacktiago.carplate.demo.date.DateEvaluator;
import com.blacktiago.carplate.demo.date.SimpleDateEvaluator;
import com.blacktiago.carplate.demo.engine.repository.LocalPlateConfigRepository;
import com.blacktiago.carplate.demo.engine.repository.PlateConfigRepository;
import com.blacktiago.carplate.demo.plate.PlateEvaluator;
import com.blacktiago.carplate.demo.plate.SimplePlateEvaluator;
import com.blacktiago.carplate.demo.time.SimpleTimeEvaluator;
import com.blacktiago.carplate.demo.time.TimeEvaluator;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class SimplePredictor implements Predictor{

    private DateEvaluator dateEvaluator = new SimpleDateEvaluator();
    private PlateEvaluator plateEvaluator = new SimplePlateEvaluator();
    private TimeEvaluator timeEvaluator = new SimpleTimeEvaluator();
    private PlateConfigRepository configRepository = new LocalPlateConfigRepository();

    private Date date;
    private int dayOfWeek;
    private int lastPlateDigit;
    private int hour;
    private int minute;
    private PlateRestrictionConfig plateConfig;

    public boolean isAllowed(String plate, String date, String time){
        boolean allowed = false;
        processDate(date);
        processPlate(plate);
        processTime(time);
        this.plateConfig = configRepository.loadConfig();
        return allowed;
    }

    private void processTime(String time) {
        if(timeEvaluator.isValidTime(time)){
            this.hour = timeEvaluator.hourOf(time);
            this.minute = timeEvaluator.minuteOf(time);
        } else {
            log.error("Time "+time+" is not valid, please use format like "+timeEvaluator.validTimeExample());
        }
    }

    private void processPlate(String plate) {
        if(plateEvaluator.isValidPlate(plate)){
            this.lastPlateDigit = plateEvaluator.getEvaluationDigit(plate);
        } else {
            log.error("Plate "+plate+" is not valid, please use format like "+plateEvaluator.validPlateExample());
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
            log.error("Date "+date+" is not valid, please use format like "+dateEvaluator.allowedFormat());
        }
    }
}
