package com.example.liumeng.quanminfu2.javaTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by liumeng on 2016/12/22 on 17:25
 * 反射
 *包含: 暴力反射获取字段和方法    非暴力获取字段和方法
 * 方法有参数且有返回值 没有参数和返回值
 */
public class Reflect {
    public static void main(String[] args) throws Exception {
        Class<Bereflect> bereflectClass = Bereflect.class;
        //非暴力获取方法
        //问题1 当无参数的时候第二个参数怎么传
        Method study = bereflectClass.getMethod("study");
        //创建Berefect的对象
        Bereflect bereflect = bereflectClass.newInstance();
        study.invoke(bereflect);

        //获取字段修改并删除
        Field age = bereflectClass.getField("age");
        age.set(bereflect,13);
        System.out.println("age = "+bereflect.age);

        //暴力获取传参且有返回值的方法
        Method getName = bereflectClass.getDeclaredMethod("getName", String.class);
        //一定不能少了这一句
        getName.setAccessible(true);
        Object invoke = getName.invoke(bereflect, "刘蒙");
        System.out.println(invoke);
    }
}
