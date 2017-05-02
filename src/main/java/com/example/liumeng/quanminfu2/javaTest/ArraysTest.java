package com.example.liumeng.quanminfu2.javaTest;

import com.example.liumeng.quanminfu2.javaTest.bean.Person;

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
//        Zi z = new Zi();

//        if (!false && !true && !false){
//            System.out.println("liumeng");
//        }else {
//
//        }

//        ArrayList<String> strings = new ArrayList<>();
//        System.out.println(strings.get(0));
//        String substring = "012".substring(1,2);
//        System.out.println(substring);
//        Person.name
        Person.age = 20;
        Person person = new Person();
        person.name = "liumeng";
        Person person1 = new Person();

        System.out.println(person.age+"年纪");
        System.out.println(person1.age+"年纪");
        System.out.println("性别"+person.sex);
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
