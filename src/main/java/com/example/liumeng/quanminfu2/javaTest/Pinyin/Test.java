package com.example.liumeng.quanminfu2.javaTest.Pinyin;

import java.util.ArrayList;

/**
 * Created by liumeng on 2017/4/21 on 17:43
 */

public class Test {
    public static void main(String args[]){
        ArrayList<HanziToPinyin.Token> list = HanziToPinyin.getInstance().get(
                "单刘蒙");
        for (HanziToPinyin.Token token : list)
        {
            System.out.print(token.source + " , " + token.target + " , " + token.type);
            System.out.println();
        }
    }
}
