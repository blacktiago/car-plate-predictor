package com.blacktiago.carplate.demo.rest;


import com.blacktiago.carplate.demo.engine.Prediction;
import com.blacktiago.carplate.demo.engine.Predictor;
import com.blacktiago.carplate.demo.engine.SimplePredictor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictorRestController {

    @GetMapping("predict")
    public Prediction canDrive(@RequestParam(value = "plate") String plate,
                               @RequestParam(value = "date") String date,
                               @RequestParam(value = "time") String time){

        Predictor predictor = new SimplePredictor();
        return predictor.canDrive(plate, date, time);
    }

}
