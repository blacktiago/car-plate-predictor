package com.blacktiago.carplate.demo;

import com.blacktiago.carplate.demo.date.DateEvaluator;
import com.blacktiago.carplate.demo.date.SimpleDateEvaluator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DateValueTests {

    private DateEvaluator dateEvaluator = new SimpleDateEvaluator();


    @Test
    void validFormat(){
        Assert.assertTrue(dateEvaluator.isValidFormat("14-12-2020"));
    }

    @Test
    void invalidFormat(){
        Assert.assertFalse(dateEvaluator.isValidFormat("2020-21-14"));
        Assert.assertFalse(dateEvaluator.isValidFormat("14-13-2020"));
        Assert.assertFalse(dateEvaluator.isValidFormat("32-12-2020"));
        Assert.assertFalse(dateEvaluator.isValidFormat("32-12"));
        Assert.assertFalse(dateEvaluator.isValidFormat("4/12/2020"));
    }

}
