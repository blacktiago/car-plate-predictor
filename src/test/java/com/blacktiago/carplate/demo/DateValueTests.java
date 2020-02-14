package com.blacktiago.carplate.demo;

import com.blacktiago.carplate.demo.date.DateEvaluator;
import com.blacktiago.carplate.demo.date.SimpleDateEvaluator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class DateValueTests {

    private DateEvaluator dateEvaluator = new SimpleDateEvaluator();


    @Test
    void validFormat(){
        Assert.isTrue(dateEvaluator.isValidFormat("14-12-2020"), "Valid as suggested chain");
    }

    @Test
    void invalidFormat(){
        Assert.isTrue(!dateEvaluator.isValidFormat("2020-21-14"), "Not valid order");
        Assert.isTrue(!dateEvaluator.isValidFormat("14-13-2020"), "Not valid month");
        Assert.isTrue(!dateEvaluator.isValidFormat("32-12-2020"), "Not valid day");
        Assert.isTrue(!dateEvaluator.isValidFormat("32-12"), "Not valid incomplete");
        Assert.isTrue(!dateEvaluator.isValidFormat("4/12/2020"), "Not valid different separator");
    }

}
