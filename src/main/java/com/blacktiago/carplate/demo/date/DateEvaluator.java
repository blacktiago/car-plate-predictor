package com.blacktiago.carplate.demo.date;

import java.text.ParseException;
import java.util.Date;

public interface DateEvaluator {

    Date parse(String date) throws ParseException;

    boolean isValidFormat(String date);

    String allowedFormat();

}
