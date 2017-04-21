package com.example.liumeng.quanminfu2.javaTest;

import com.example.liumeng.quanminfu2.javaTest.bean.Zi;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Created by liumeng on 2017/1/3 on 10:18
 */
public class ArraysTest {
    public static void main(String[] args) {
//        long time = 1483357196224l;
//        String formatTime = getFormatTime(time);
//        System.out.println("formatime = "+formatTime);
//        int maxValue = getMaxValue3(4, 5, 6);
//        System.out.println(maxValue+"");
        //验证代码块
        Zi z = new Zi();

    }
    private static String getFormatTime(long time){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日  HH:mm");
        String format = simpleDateFormat.format(time);
        return format;
    }

    public static int getMaxValue(int a, int b, int c) {
        int maxValue = 0 ;
        if (a > b) {
            if (a > c) {
                maxValue = a;
            } else {
                maxValue = c;
            }
        } else {
            if (b > c) {
                maxValue = b;
            } else {
                maxValue = c;
            }
        }
        return maxValue;
    }

    public static int getMaxValue2(int a, int b, int c) {
        int maxValue = 0 ;
        maxValue = a>b ? (a>c?a:c):(b>c?b:c);
        return maxValue;
    }

    public static int getMaxValue3(int a, int b, int c) {
        int maxValue = 0 ;
        int arr[] = {a,b,c};
        Arrays.sort(arr);
        maxValue = arr[2];
        return maxValue;
    }
}
