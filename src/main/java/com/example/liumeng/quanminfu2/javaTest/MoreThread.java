package com.example.liumeng.quanminfu2.javaTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MoreThread {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        Future<?> submit = threadPool.submit(new MyRunnable());
        Future<?> submit1 = threadPool.submit(new MyRunnable());

        //关闭线程池 不再接受新的线程
        threadPool.shutdown();
 
    }
 
 
 
}
class MyRunnable implements Runnable{
 
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
 
            System.out.println(Thread.currentThread().getName()+"........"+i);
        }
    }
 
}