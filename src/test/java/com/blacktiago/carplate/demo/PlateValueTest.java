package com.blacktiago.carplate.demo;

import com.blacktiago.carplate.demo.plate.PlateEvaluator;
import com.blacktiago.carplate.demo.plate.SimplePlateEvaluator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlateValueTest {

    private PlateEvaluator plateEvaluator = new SimplePlateEvaluator();

    @Test
    void validFormat(){
        Assert.assertTrue(plateEvaluator.isValidPlate("PRF 2351"));
        Assert.assertTrue(plateEvaluator.isValidPlate("TFE 353"));
        Assert.assertTrue(plateEvaluator.isValidPlate("Gfc 3890"));
    }

    @Test
    void invalidFormat(){
        Assert.assertFalse(plateEvaluator.isValidPlate("PRF2351"));
        Assert.assertFalse(plateEvaluator.isValidPlate("PRFw 2351"));
        Assert.assertFalse(plateEvaluator.isValidPlate("PRF 23513"));
        Assert.assertFalse(plateEvaluator.isValidPlate("PR 2351"));
        Assert.assertFalse(plateEvaluator.isValidPlate("PRF 23"));
    }

    @Test
    void validLastDigit(){
        Assert.assertEquals(1, plateEvaluator.getEvaluationDigit("PRF 2351"));
    }
}
