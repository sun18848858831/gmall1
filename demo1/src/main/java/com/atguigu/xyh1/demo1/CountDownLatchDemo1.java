package com.atguigu.xyh1.demo1;


import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo1 {
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for(int i=0;i<6;i++){
            new Thread(()->  {
                System.out.println(Thread.currentThread().getName()+"离开");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("xxxxx离开");
    }
}
