package com.example.liumeng.quanminfu2.javaTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liumeng on 2016/12/16 on 11:25
 */
//@Retention(RetentionPolicy.SOURCE)//仅仅在源码阶段生效，编译成字节码的时候就被删除了
//@Retention(RetentionPolicy.CLASS)//编译成字节码的时候该注解也被保留，但是当字节码被加载到JVM的时候，被移除
@Retention(RetentionPolicy.RUNTIME)//就算被jvm加载到内存中了，该注解依然被保留
@Target(ElementType.FIELD)//用于指定该注解类要添加到什么对象上
public @interface ViewInject {
    String name();//value是一个特殊的抽象方法名
}
