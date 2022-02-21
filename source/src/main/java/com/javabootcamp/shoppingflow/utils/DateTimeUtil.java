package com.javabootcamp.shoppingflow.utils;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
    public static Date addDay(Date date, int numberOfDays) {
        var calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, numberOfDays);
        return calendar.getTime();
    }
}
