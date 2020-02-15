package com.blacktiago.carplate.demo;

import com.blacktiago.carplate.demo.engine.Prediction;
import com.blacktiago.carplate.demo.engine.Predictor;
import com.blacktiago.carplate.demo.engine.SimplePredictor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootApplication
public class CarPlatePredictorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CarPlatePredictorApplication.class, args);
    }

    @Override
    public void run(String... args){
        log.info("Welcome to car plate predictor ");

        List<String> inputArguments = Arrays.asList(args);
        int functionRunIndex = inputArguments.indexOf("-dry");

        if(functionRunIndex != -1){
            Predictor predictor = new SimplePredictor();
            try{
                int plateIndex = inputArguments.indexOf("-p");
                String plate = args[plateIndex + 1];

                int dateIndex = inputArguments.indexOf("-d");
                String date = args[dateIndex + 1];

                int timeIndex = inputArguments.indexOf("-t");
                String time = args[timeIndex + 1];

                Prediction prediction = predictor.canDrive(plate, date, time);
                log.info(prediction.getMessage());
            } catch (Exception ex){

                log.info("Sorry unable to prcess command, here is an example of command line usage");
                log.info(" -p \"PFB 2317\" -d \"13-02-2020\" -t \"8:30\"");
            }
            System.exit(0);
        }

    }
}
