package com.example.liumeng.quanminfu2.javaTest;

/**
 * Created by liumeng on 2016/12/22 on 17:26
 */
public class Bereflect {
    public int age;
    private String name;

    public void study() {
        System.out.println("非暴力反射方法 无返回值");
    }

    private String getName(String name) {
        return "名字是:"+name;
    }
}
