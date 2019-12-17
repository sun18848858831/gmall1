package com.atguigu.xyh1.demo1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for(int i=0;i<6;i++){
            new Thread(()->  {
                boolean flag = false;
                try
                {
                    semaphore.acquire();
                    flag = true;
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (flag){
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start();
        }
    }
}
