package com.atguigu.xyh1.demo1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo1 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for(int i=0;i<6;i++){
            new Thread(()->  {
                boolean te = false;
                try
                {
                    semaphore.acquire();
                    te = true;
                    System.out.println(Thread.currentThread().getName()+"抢到了");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"又放了");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(te){
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start();
        }
    }
}
