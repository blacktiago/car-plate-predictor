package com.blacktiago.carplate.demo;

import com.blacktiago.carplate.demo.engine.Predictor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarPlatePredictorApplicationTests {

    private Predictor predictor;

    @Test
    void isCarPlateAllowed() {
        Assert.assertTrue(predictor.isAllowed("PSD 2556", "13/02/2020", "15"));
    }

    @Test
    void isCarPlateDenied() {
        Assert.assertFalse(predictor.isAllowed("PTY 2557", "13/02/2020", "15"));
    }

}
