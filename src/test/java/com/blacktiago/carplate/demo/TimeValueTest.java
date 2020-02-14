package com.blacktiago.carplate.demo;

import com.blacktiago.carplate.demo.time.SimpleTimeEvaluator;
import com.blacktiago.carplate.demo.time.TimeEvaluator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class TimeValueTest {

    TimeEvaluator timeEvaluator = new SimpleTimeEvaluator();

    @Test
    void validFormat(){
        Assert.isTrue(timeEvaluator.isValidTime("03"), "03 am test");
        Assert.isTrue(timeEvaluator.isValidTime("5"), "5 am test");
        Assert.isTrue(timeEvaluator.isValidTime("12"), "mid day");
        Assert.isTrue(timeEvaluator.isValidTime("16"), "16 pm");
        Assert.isTrue(timeEvaluator.isValidTime("20"), "20 pm");
        Assert.isTrue(timeEvaluator.isValidTime("00"), "mid night");
    }
}
