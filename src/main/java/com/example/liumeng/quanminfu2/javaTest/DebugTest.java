package com.example.liumeng.quanminfu2.javaTest;

import android.text.TextUtils;

/**
 * Created by liumeng on 2017/5/2 on 10:47
 */

public class DebugTest {

    public static void main(String args[]){
        int  liumeng  =  7;
        int  liumeng3  =  7;
        int  liume2332ng  =  7;
        int  li323umeng  =  7;
        int  l3iumeng  =  7;
        int  iumen23g  =  7;
        int  l6iumeng  =  7;

        System.out.println("我是刘蒙");
    }

    private static void stepNext(int i) {
        System.out.println("stepNext当中i的值"+i);
    }

    public void init() {
        boolean equals = TextUtils.equals("a","b");
    }
}
