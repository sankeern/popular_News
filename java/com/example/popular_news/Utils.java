package com.example.popular_news;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Utils {

    public static ColorDrawable[] vibrantLightColorList =
            {
                    new ColorDrawable(Color.parseColor("#ffeead")),
                    new ColorDrawable(Color.parseColor("#93cfb3")),
                    new ColorDrawable(Color.parseColor("#fd7a7a")),
                    new ColorDrawable(Color.parseColor("#faca5f")),
                    new ColorDrawable(Color.parseColor("#1ba798")),
                    new ColorDrawable(Color.parseColor("#6aa9ae")),
                    new ColorDrawable(Color.parseColor("#ffbf27")),
                    new ColorDrawable(Color.parseColor("#d93947"))
            };

    public static ColorDrawable getRandomDrawbleColor() {
        int idx = new Random().nextInt(vibrantLightColorList.length);
        return vibrantLightColorList[idx];
    }

    public static String DateToTimeFormat(String oldstringDate){
        PrettyTime p = new PrettyTime(new Locale(getCountry()));
        String isTime = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
                    Locale.ENGLISH);
            Date date = sdf.parse(oldstringDate);
            isTime = p.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isTime;
    }

    public static String DateFormat(String oldstringDate){
        String newDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy", new Locale(getCountry()));
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate);
            newDate = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            newDate = oldstringDate;
        }

        return newDate;
    }
    public static String getTodayDate(String publishedAt) {
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy", Locale.getDefault());
        return dateFormat.format(today);
    }

    public static String convertToIST(String publishedAt) {
        LocalDateTime dateTime = null; // current date and time in default timezone
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dateTime = LocalDateTime.now();
        }
        ZoneId sourceZone = null; // source timezone is the default timezone
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sourceZone = ZoneId.systemDefault();
        }
        ZoneId targetZone = null; // target timezone is IST
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            targetZone = ZoneId.of("Asia/Kolkata");
        }
        ZonedDateTime sourceZonedDateTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sourceZonedDateTime = dateTime.atZone(sourceZone);
        }
        ZonedDateTime targetZonedDateTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            targetZonedDateTime = sourceZonedDateTime.withZoneSameInstant(targetZone);
        }
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("E, d MMM yyyy HH:mm:ss z");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return formatter.format(targetZonedDateTime);
        }
        return publishedAt;
    }

    public static String getCountry(){
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }

    public static String getLanguage() {

        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getLanguage());
        return country.toLowerCase();

    }
}




