package com.example.liumeng.quanminfu2.javaTest;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by liumeng on 2017/1/9 on 9:13
 */

public class CurentModifyTest {
    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("d");
        list.add("f");
        list.add("e");
        list.add("b");
        for (int i = 0; i < list.size(); i++) {
//            boolean a = list.remove("a");
            String s = list.get(i);
//            System.out.println(s);
        }
        for (String s : list) {
//            list.remove("a");
//            System.out.println(s);
        }
//        Iterator<String> iterator = list.iterator();
        Iterator<String> stringListIterator = list.iterator();
        while (stringListIterator.hasNext()) {
//            list.remove("a");
            String next = stringListIterator.next();
            if (next.equals("a")) {
                stringListIterator.remove();
            } else {
                System.out.println(next);
            }
        }
    }
}
