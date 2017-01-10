package com.example.liumeng.quanminfu2.javaTest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by liumeng on 2016/12/16 on 13:41
 */
public class AnnotationTest {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        User user = new User();
        Class<User> clazz = User.class;
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ViewInject vi = field.getAnnotation(ViewInject.class);
            if (vi != null) {
                String value = vi.name();
                field.setAccessible(true);
                field.set(user,value);
                System.out.println("value="+value);
            }
        }
        System.out.println(user.toString());

        //使用反射调用eat方法
        Method eat = clazz.getDeclaredMethod("eat", String.class);
        //当eat方法为public的时候就不需要这一句,如果eat是private的就需要暴力反射
        eat.setAccessible(true);
        Object object = eat.invoke(user, "西瓜");
        System.out.println("调用结果为: "+object);
    }
}
