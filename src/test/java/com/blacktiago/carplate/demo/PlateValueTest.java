package com.blacktiago.carplate.demo;

import com.blacktiago.carplate.demo.plate.PlateEvaluator;
import com.blacktiago.carplate.demo.plate.SimplePlateEvaluator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class PlateValueTest {

    private PlateEvaluator plateEvaluator = new SimplePlateEvaluator();

    @Test
    void validFormat(){
        Assert.isTrue(plateEvaluator.isValidPlate("PRF 2351"), "Valid 4 numbers");
        Assert.isTrue(plateEvaluator.isValidPlate("TFE 353"), "Valid 3 numbers");
        Assert.isTrue(plateEvaluator.isValidPlate("Gfc 3890"), "Valid different key sensitive");
    }

    @Test
    void invalidFormat(){
        Assert.isTrue(!plateEvaluator.isValidPlate("PRF2351"), "Space is required");
        Assert.isTrue(!plateEvaluator.isValidPlate("PRFw 2351"), "To much letters");
        Assert.isTrue(!plateEvaluator.isValidPlate("PRF 23513"), "To much numbers");
        Assert.isTrue(!plateEvaluator.isValidPlate("PR 2351"), "Not enough letters");
        Assert.isTrue(!plateEvaluator.isValidPlate("PRF 23"), "Not enough numbers");
    }

    @Test
    void validLastDigit(){
        Assert.isTrue(plateEvaluator.getEvaluationDigit("PRF 2351") == 1, "Valid last Digit");
    }
}
