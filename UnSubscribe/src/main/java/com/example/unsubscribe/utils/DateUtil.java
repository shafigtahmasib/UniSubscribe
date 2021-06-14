package com.example.unsubscribe.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static int remainingDays(LocalDate date) throws ParseException {
        LocalDate now = LocalDate.now();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date currentDate = sdf.parse(now.toString());
        Date second = sdf.parse(date.toString());

        return (int) ((second.getTime()-currentDate.getTime())/86400000);
    }

}
