package com.blacktiago.carplate.demo;

import com.blacktiago.carplate.demo.time.SimpleTimeEvaluator;
import com.blacktiago.carplate.demo.time.TimeEvaluator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TimeValueTest {

    private TimeEvaluator timeEvaluator = new SimpleTimeEvaluator();

    @Test
    void validFormat(){
        Assert.assertTrue(timeEvaluator.isValidTime("00:00"));
        Assert.assertTrue(timeEvaluator.isValidTime("03:15"));
        Assert.assertTrue(timeEvaluator.isValidTime("05:30"));
        Assert.assertTrue(timeEvaluator.isValidTime("12:00"));
        Assert.assertTrue(timeEvaluator.isValidTime("16:20"));
        Assert.assertTrue(timeEvaluator.isValidTime("20:45"));
    }

    @Test
    void invalidFormat(){
        Assert.assertTrue(!timeEvaluator.isValidTime("sdfd"));
        Assert.assertTrue(!timeEvaluator.isValidTime("200"));
        Assert.assertTrue(!timeEvaluator.isValidTime("-1"));
        Assert.assertTrue(!timeEvaluator.isValidTime("24:00"));
        Assert.assertTrue(!timeEvaluator.isValidTime("12:60"));
    }
}
