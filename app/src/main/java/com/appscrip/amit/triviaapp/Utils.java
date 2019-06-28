package com.appscrip.amit.triviaapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String convertTimeStampToDate(long time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM hh:mm a");
            return formatter.format(new Date(Long.parseLong(String.valueOf(time))));
        }catch (Exception e){
            e.printStackTrace();
            return "Today";
        }
    }
}
