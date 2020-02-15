package com.blacktiago.carplate.demo;

import com.blacktiago.carplate.demo.engine.Prediction;
import com.blacktiago.carplate.demo.engine.Predictor;
import com.blacktiago.carplate.demo.engine.SimplePredictor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarPlatePredictorApplicationTests {

    private Predictor predictor = new SimplePredictor();

    @Test
    void isCarPlateAllowed() {
        Assert.assertTrue(predictor.isAllowed("PSD 2556", "13-02-2020", "08:30"));
        Assert.assertTrue(predictor.isAllowed("PSD 2556", "13-02-2020", "08:30"));
        Assert.assertTrue(predictor.isAllowed("PSD 2557", "11-02-2020", "08:30"));
        Assert.assertTrue(predictor.isAllowed("PSD 2557", "13-02-2020", "14:45"));

    }

    @Test
    void isCarPlateDenied() {
        Assert.assertFalse(predictor.isAllowed("PTY 2557", "13-02-2020", "08:30"));
    }

    @Test
    void isCarPlateAllowedValueObject() {
        Prediction prediction = predictor.canDrive("PSD 2556", "13-02-2020", "08:30");
        Assert.assertTrue(prediction.isAllowed());

        prediction = predictor.canDrive("PSD 2557", "11-02-2020", "08:30");
        Assert.assertTrue(prediction.isAllowed());

        prediction = predictor.canDrive("PSD 2557", "13-02-2020", "14:45");
        Assert.assertTrue(prediction.isAllowed());

    }

    @Test
    void isCarPlateDeniedValueObject() {
        Prediction prediction = predictor.canDrive("PTY 2557", "13-02-2020", "08:30");
        Assert.assertFalse(prediction.isAllowed());
    }

}
