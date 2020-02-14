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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

@Slf4j
public class SimplePredictor implements Predictor{

    private static final String NOT_VALID_FORMAT = " is not valid, please use format like ";
    private DateEvaluator dateEvaluator = new SimpleDateEvaluator();
    private PlateEvaluator plateEvaluator = new SimplePlateEvaluator();
    private TimeEvaluator timeEvaluator = new SimpleTimeEvaluator();
    private PlateConfigRepository configRepository = new LocalPlateConfigRepository();
    private PlateRestrictionConfig plateConfig;

    private int dayOfWeek = -1;
    private int lastPlateDigit;
    private String time;

    public boolean isAllowed(String plate, String date, String time){
        processDate(date);
        processPlate(plate);
        processTime(time);
        plateConfig = configRepository.loadConfig();

        boolean dayOfControl = plateConfig.days.containsKey(dayOfWeek);
        boolean dayMatchWithPlate = plateConfig.days.get(dayOfWeek).contains(lastPlateDigit);
        boolean isInControlHours = (validateHour("am") || validateHour("pm"));

        return !(dayOfControl && dayMatchWithPlate && isInControlHours);
    }

    private boolean validateHour(String timeKey) {
        boolean isValid = true;
        if(plateConfig.time.containsKey(timeKey)){
            HashMap<String, String> timeItem =plateConfig.time.get(timeKey);
            try {
                Date time1 = dateOf(timeItem.get("begin"));
                Date time2 = dateOf(timeItem.get("end"));
                Date now = dateOf(time);

                isValid = now.after(time1) && now.before(time2);
            } catch (ParseException e) {
                log.error("Unable to evaluate time for "+timeKey, e);
                isValid = false;
            }

        }
        return isValid;
    }

    private Date dateOf(String timeArgument) throws ParseException {
        Date time1 = new SimpleDateFormat("HH:mm").parse(timeArgument);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time1);
        calendar1.add(Calendar.DATE, 1);
        return time1;
    }

    private void processTime(String time) {
        if(timeEvaluator.isValidTime(time)){
            this.time = time;
        } else {
            log.error("Time "+time+ NOT_VALID_FORMAT +timeEvaluator.validTimeExample());
        }
    }

    private void processPlate(String plate) {
        if(plateEvaluator.isValidPlate(plate)){
            this.lastPlateDigit = plateEvaluator.getEvaluationDigit(plate);
        } else {
            log.error("Plate "+plate+ NOT_VALID_FORMAT +plateEvaluator.validPlateExample());
        }
    }

    private void processDate(String date) {
        try{
            if(dateEvaluator.isValidFormat(date)){
                Locale aLocale = new Locale.Builder().setLanguage("es").setRegion("EC").build();
                Calendar c = Calendar.getInstance(aLocale);
                Date parsedDate = dateEvaluator.parse(date);
                c.setTime(parsedDate);
                dayOfWeek  = c.get(Calendar.DAY_OF_WEEK);
            }
        } catch (Exception ex){
            log.error("Date "+date+ NOT_VALID_FORMAT +dateEvaluator.allowedFormat());
        }
    }

    public String plateExample(){
        return plateEvaluator.validPlateExample();
    }

    public String dateExample(){
        return "31-12-2020";
    }

    public String timeExample(){
        return timeEvaluator.validTimeExample();
    }
}
