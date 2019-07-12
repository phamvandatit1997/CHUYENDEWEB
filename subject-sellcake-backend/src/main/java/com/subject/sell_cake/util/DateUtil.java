package com.subject.sell_cake.util;

import java.util.Date;

import org.joda.time.DateTimeZone;

public class DateUtil {

    public static Date convertToUTC(Date date)
    {
        DateTimeZone d = DateTimeZone.getDefault();
        return new Date(d.convertLocalToUTC(date.getTime(),false));
    }
}
