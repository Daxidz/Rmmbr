package com.daxid.rmmbr.utils;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Date;

/**
 * Created by David on 13.03.2017.
 */

public class DateUtils {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static GregorianCalendar convertFromDMY(String dd_mm_yy) throws java.text.ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        Date date = fmt.parse(dd_mm_yy);
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String format(GregorianCalendar calendar){
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }
}
