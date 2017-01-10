package com.example.liumeng.quanminfu2.javaTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liumeng on 2017/1/10 on 11:28
 */
//重点:printer和this表示的是同一个对象
public class Synchrolock {
    public static void main(String[] args) {
//        final Printer printer = new Printer();
        new Thread(){
            @Override
            public void run() {
                print();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                print();
            }
        }.start();
//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        printer.print1();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }.start();
//        new Thread() {
//
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        printer.print2();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        printer.print3();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();

    }

    private static synchronized void print(){
        System.out.println("第一句");
//        SystemClock.sleep(300);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第二句");
    }

}

class Printer {
    //标志位
    private int flags = 1;
    ReentrantLock r = new ReentrantLock();
    private Condition c1 = r.newCondition();
    private Condition c2 = r.newCondition();
    private Condition c3 = r.newCondition();
    public void print1() throws InterruptedException {
//        synchronized (this) {
        r.lock();
            //在这个地方if和while的区别,用if的话当唤醒其他线成的时候不会再次判断这个标志位是否正确,因为if只会走一次
        while (flags != 1) {
//                this.wait();
                c1.await();
            }
            System.out.print("第");
            System.out.print("一");
            System.out.print("行");
            System.out.print("数");
            System.out.print("据");
            System.out.println();
            //改变标志位并唤醒其他线程
            flags = 2;
            c2.signal();
//            this.notifyAll();
//        }
    }

    public void print2() throws InterruptedException {
//        synchronized (this) {
        r.lock();

        while (flags != 2) {
//                this.wait();
                c2.await();
            }
            System.out.print("我");
            System.out.print("是");
            System.out.print("第");
            System.out.print("二");
            System.out.print("行");
            System.out.println();
            //改变标志位并唤醒其他线程
            flags = 3;
            c3.signal();
//            this.notifyAll();
        r.unlock();
//        }
    }

    public void print3() throws InterruptedException {
//        synchronized (this) {
        r.lock();

        while (flags != 3) {
//                this.wait();
                c3.await();
            }
            System.out.print("第");
            System.out.print("三");
            System.out.print("行");
            System.out.print("数");
            System.out.print("据");
            System.out.println();
            //改变标志位并唤醒其他线程
            flags = 1;
            c1.signal();
//            this.notifyAll();
        r.unlock();
//        }
    }
}

