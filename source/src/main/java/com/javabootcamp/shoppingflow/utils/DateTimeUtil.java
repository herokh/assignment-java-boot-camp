package com.javabootcamp.shoppingflow.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
    public static Date addDay(Date date, int numberOfDays) {
        var calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, numberOfDays);
        return calendar.getTime();
    }

    public static String convertDateToString(Date dt, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        String dateToString = df.format(dt);
        return dateToString;
    }
}
