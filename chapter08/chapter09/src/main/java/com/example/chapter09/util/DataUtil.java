package com.example.chapter09.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
@SuppressLint("SimpleDateFormat")
public class DataUtil {

    public static String getNowDataTime(String formatStr){
        String format = formatStr;
        if (TextUtils.isEmpty(formatStr)){
            format = "yyyyMMddHHmmss";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }

    public static String getNowDataTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }

    public static String getNowTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
