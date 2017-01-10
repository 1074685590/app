package com.example.liumeng.quanminfu2.javaTest;

//加上互斥锁以后： Synchrolock
public class LockDemo2 {
 
    private static Object lock = new Object(); // static确保只有一把锁
    private int i = 0;
 
    public void increaseI() {
        synchronized (lock) {
            for(int k=0;k<10;k++) {  // 对i执行10次增1操作
                i++;
            }
            System.out.println(Thread.currentThread().getName() + "线程，i现在的值：" + i);
        }
    }
     
    public static void main(String[] args) {
        LockDemo2 ld = new LockDemo2();
         
        int threadNum = 1000;   // 选择1000个线程让结果更加容易观测到
        MyThread2[] threads = new MyThread2[threadNum];
        for(int i=0;i<threads.length;i++) {
            threads[i] = new MyThread2(ld);  // 所有线程共用一个LockDemo对象
            threads[i].start();
        }
    }
}
 
class MyThread2 extends Thread {
    LockDemo2 ld;
     
    public MyThread2(LockDemo2 ld) {
        this.ld = ld;
    }
     
    public void run() {
        ld.increaseI();
    }
}