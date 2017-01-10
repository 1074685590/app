package com.example.liumeng.quanminfu2.javaTest;

/**
 * Created by liumeng on 2016/11/28 on 17:26
 * 单例
 */
public class Test
{
    public static void main(String[] args) {

    }

    static class Single{
        private static Single mSingle = new Single();
        private Single() {}
        public static Single getSingle(){
            return mSingle;
        }
    }
}
class Single{
    private Single(){} //私有化构造函数。
    private static Single s = new Single(); //创建私有并静态的本类对象。
    public static Single getInstance(){ //定义公有并静态的方法，返回该对象。
        return s;
    }
}