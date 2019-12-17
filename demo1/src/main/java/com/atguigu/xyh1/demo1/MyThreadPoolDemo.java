package com.atguigu.xyh1.demo1;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
       /* ExecutorService threadPool = Executors.newFixedThreadPool(3);//一池3线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();//一池1线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();//一池n线程*/
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try
        {
            for (int i = 0; i <20 ; i++) {
                final int te = i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"客户号"+te);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
