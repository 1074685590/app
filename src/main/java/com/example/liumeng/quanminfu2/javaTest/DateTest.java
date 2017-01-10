package com.example.liumeng.quanminfu2.javaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liumeng on 2016/12/28 on 9:37
 */
public class DateTest {
    public static void main(String[] args) throws ParseException {
        String date = "201-1-2-3";
        System.out.println(formatTimeOne(date));
    }

    public static String formatTimeOne(String date) throws ParseException {

        if (date == null ) {
            return "";
        } else {
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = dateFormater.parse(date);
            SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy年MM月dd日");
            return  dateFormater2.format(parse);
        }
    }
}
