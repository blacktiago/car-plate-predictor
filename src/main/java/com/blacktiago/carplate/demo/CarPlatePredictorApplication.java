package com.blacktiago.carplate.demo;

import com.blacktiago.carplate.demo.engine.Predictor;
import com.blacktiago.carplate.demo.engine.SimplePredictor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.MessageFormat;
import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class CarPlatePredictorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CarPlatePredictorApplication.class, args);
    }

    @Override
    public void run(String... args){
        log.info("Welcome to car plate predictor ");


        try{
            int plateIndex = Arrays.asList(args).indexOf("-p");
            String plate = args[plateIndex + 1];

            int dateIndex = Arrays.asList(args).indexOf("-d");
            String date = args[dateIndex + 1];

            int timeIndex = Arrays.asList(args).indexOf("-t");
            String time = args[timeIndex + 1];

            Predictor predictor = new SimplePredictor();
            String[] arguments = {plate, date, time};

            if(predictor.isAllowed(plate, date, time)){

                String message = "Congrats your car with plate {0} is allowed to go out at {1} of {2}";
                MessageFormat mf = new MessageFormat(message);
                log.info(mf.format(arguments));
            } else {

                String message = "Sorry your car with plate {0} is not allowed on date {1} at {2}";
                MessageFormat mf = new MessageFormat(message);
                log.info(mf.format(arguments));
            }
        } catch (Exception ex){
            log.info("Sorry unable to prcess command, here is an example of command line usage");
            log.info(" -p \"PFB 2317\" -d \"13-02-2020\" -t \"8:30\"");
        }

    }
}
