package com.atguigu.xyh1.demo1;

import java.util.concurrent.*;

public class MyThreadPoolDemo1 {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(
                2,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try
        {
            for (int i = 0; i <8 ; i++) {
                final int te = i;
                es.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"客户"+te);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            es.shutdown();
        }
        /*ExecutorService es = Executors.newCachedThreadPool();
        try
        {
            for (int i = 0; i <20 ; i++) {
                final int te = i;
                es.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"客户"+te);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            es.shutdown();
        }*/
       /* ExecutorService es = Executors.newSingleThreadExecutor();
        try
        {
            for (int i = 0; i <20 ; i++) {
                final int te = i;
                es.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"客户"+te);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            es.shutdown();
        }*/

        /*ExecutorService es = Executors.newFixedThreadPool(3);
        try
        {
            for (int i = 0; i < 20; i++) {
                final int te = i;
                es.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "客户" + te);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            es.shutdown();
        }*/
    }
}
