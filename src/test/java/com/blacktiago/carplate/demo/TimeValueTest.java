package com.blacktiago.carplate.demo;

import com.blacktiago.carplate.demo.time.SimpleTimeEvaluator;
import com.blacktiago.carplate.demo.time.TimeEvaluator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class TimeValueTest {

    private TimeEvaluator timeEvaluator = new SimpleTimeEvaluator();

    @Test
    void validFormat(){
        Assert.isTrue(timeEvaluator.isValidTime("00:00"), "mid night");
        Assert.isTrue(timeEvaluator.isValidTime("03:15"), "03 am test");
        Assert.isTrue(timeEvaluator.isValidTime("05:30"), "5 am test");
        Assert.isTrue(timeEvaluator.isValidTime("12:00"), "mid day");
        Assert.isTrue(timeEvaluator.isValidTime("16:20"), "16 pm");
        Assert.isTrue(timeEvaluator.isValidTime("20:45"), "20 pm");
    }

    @Test
    void invalidFormat(){
        Assert.isTrue(!timeEvaluator.isValidTime("sdfd"), "letters");
        Assert.isTrue(!timeEvaluator.isValidTime("200"), "just one number");
        Assert.isTrue(!timeEvaluator.isValidTime("-1"), "negative");
        Assert.isTrue(!timeEvaluator.isValidTime("24:00"), "hour longer than 23");
        Assert.isTrue(!timeEvaluator.isValidTime("12:60"), "minute longer than 59");
    }
}
