package com.zsoltdavid.weatherlocation.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 2016.09.30..
 */
public class DateUtil {
    public static String getTimeFromTimestamp(long time) {
        try {
            DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            Date netDate = (new Date(time * 1000));
            return sdf.format(netDate);
        } catch (Exception ex) {
            return "";
        }
    }

    public static String getDateFromTimestamp(long time){
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            Date netDate = (new Date(time * 1000));
            return sdf.format(netDate);
        } catch (Exception ex) {
            return "";
        }
    }
}
