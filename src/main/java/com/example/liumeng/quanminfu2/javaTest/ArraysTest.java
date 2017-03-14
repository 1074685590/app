package com.example.liumeng.quanminfu2.javaTest;

import java.text.SimpleDateFormat;

/**
 * Created by liumeng on 2017/1/3 on 10:18
 */
public class ArraysTest {
    public static void main(String[] args) {
        long time = 1483357196224l;
        String formatTime = getFormatTime(time);
        System.out.println("formatime = "+formatTime);
    }
    private static String getFormatTime(long time){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日  HH:mm");
        String format = simpleDateFormat.format(time);
        return format;
    }

}
