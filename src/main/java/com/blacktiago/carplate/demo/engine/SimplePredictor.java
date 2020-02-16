package com.blacktiago.carplate.demo.engine;

import com.blacktiago.carplate.demo.date.*;
import com.blacktiago.carplate.demo.engine.repository.LocalPlateConfigRepository;
import com.blacktiago.carplate.demo.engine.repository.PlateConfigRepository;
import com.blacktiago.carplate.demo.plate.PlateEvaluator;
import com.blacktiago.carplate.demo.plate.SimplePlateEvaluator;
import com.blacktiago.carplate.demo.time.SimpleTimeEvaluator;
import com.blacktiago.carplate.demo.time.TimeEvaluator;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
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
    private int lastPlateDigit = -1;
    private String time;

    private Prediction prediction = new Prediction();

    @Override
    public boolean isAllowed(String plate, String date, String time){

        plateConfig = configRepository.loadConfig();

        prediction.setAllowed(validArguments(plate, date, time));

        if(prediction.isAllowed()){
            evaluateRules(plate, date, time);
        }

        return prediction.isAllowed();
    }

    private void evaluateRules(String plate, String date, String time) {
        String message;
        if(plateConfig.days.containsKey(dayOfWeek) &&
                plateConfig.days.get(dayOfWeek).contains(lastPlateDigit) &&
                (validateHour("am") || validateHour("pm"))){

            prediction.setAllowed(false);
            message = userMessage("Sorry your car with plate {0} is not allowed on date {1} at {2}",
                    plate, date, time);
        } else {
            prediction.setAllowed(true);
            message = userMessage("Congrats your car with plate {0} is allowed to go out at {1} of {2}",
                    plate, time, date);
        }
        prediction.setMessage(message);
    }

    private boolean validArguments(String plate, String date, String time) {
        processDate(date);
        processPlate(plate);
        processTime(time);

        return (dayOfWeek > 0 && dayOfWeek <= 7 ) &&
                (lastPlateDigit >= 0  && lastPlateDigit <= 9 ) &&
                time != null;
    }

    @Override
    public Prediction canDrive(String plate, String date, String time) {
        isAllowed(plate, date, time);
        return prediction;
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
                String message  = userMessage("Unable to evaluate time for {0}", timeKey);
                prediction.setMessage(message);
                log.error(message, e);
                isValid = false;
            }

        }
        return isValid;
    }

    private String userMessage(String message, String... args) {
        MessageFormat mf = new MessageFormat(message);
        return mf.format(args);
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
            String message  = userMessage("Time {0} {1} {2}", time, NOT_VALID_FORMAT, timeEvaluator.validTimeExample());
            prediction.setMessage(message);
            log.error(message);
        }
    }

    private void processPlate(String plate) {
        if(plateEvaluator.isValidPlate(plate)){
            this.lastPlateDigit = plateEvaluator.getEvaluationDigit(plate);
        } else {
            String message  = userMessage("Plate {0} {1} {2}", plate, NOT_VALID_FORMAT, plateEvaluator.validPlateExample());
            prediction.setMessage(message);
            log.error(message);
        }
    }

    private void processDate(String date) {
        String message  = userMessage("Date {0} {1} {2}", date, NOT_VALID_FORMAT, dateEvaluator.allowedFormat());

        try{
            if(dateEvaluator.isValidFormat(date)){
                Locale aLocale = new Locale.Builder().setLanguage("es").setRegion("EC").build();
                Calendar c = Calendar.getInstance(aLocale);
                Date parsedDate = dateEvaluator.parse(date);
                c.setTime(parsedDate);
                dayOfWeek  = c.get(Calendar.DAY_OF_WEEK);
            } else {
                prediction.setMessage(message);
            }
        } catch (Exception ex){
            prediction.setMessage(message);
            log.error(message, ex);
        }

    }
}