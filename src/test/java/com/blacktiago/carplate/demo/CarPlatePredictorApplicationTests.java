package com.blacktiago.carplate.demo;

import com.blacktiago.carplate.demo.engine.Predictor;
import com.blacktiago.carplate.demo.engine.SimplePredictor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CarPlatePredictorApplicationTests {

    private Predictor predictor = new SimplePredictor();

    @Test
    void isCarPlateAllowed() {
        Assert.isTrue(predictor.isAllowed("PSD 2556", "13/02/2020", "08:30"), "Allowed plate");
        Assert.isTrue(predictor.isAllowed("PSD 2557", "11/02/2020", "08:30"), "Allowed date");
        Assert.isTrue(predictor.isAllowed("PSD 2557", "13/02/2020", "14:45"), "Allowed time");

    }

    @Test
    void isCarPlateDenied() {
        Assert.isTrue(!predictor.isAllowed("PTY 2557", "13/02/2020", "08:30"), "Not allowed plate");
    }

}
