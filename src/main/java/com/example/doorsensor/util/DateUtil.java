package com.example.doorsensor.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static String format = "yyyyMMddHHmmss";


    public static LocalDateTime parse(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, formatter);
    }

    public static LocalDateTime parse(String time, String formatStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatStr);
        return LocalDateTime.parse(time, formatter);
    }
}
