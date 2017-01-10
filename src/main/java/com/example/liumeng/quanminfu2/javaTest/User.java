package com.example.liumeng.quanminfu2.javaTest;

/**
 * Created by liumeng on 2016/12/16 on 13:36
 */
public class User  {
    @ViewInject(name = "zhangsan")
    private String name;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private int age;

    private String eat(String eat) {
        return "吃的真过瘾"+eat;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + "]";
    }
}