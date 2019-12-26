package com.atguigu.xyh1.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ZyL{
    private int count = 30;
    private int cc = 0;
    private Lock lock = new ReentrantLock();
    public void mp(){
        lock.lock();
        try
        {
            if(count>0){
                System.out.println(Thread.currentThread().getName()+"卖出第"+(++cc)+",剩余"+(--count));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class sp1 {
    public static void main(String[] args) {
        ZyL zl = new ZyL();
        ExecutorService es = Executors.newFixedThreadPool(3);
        try
        {
            for (int i = 0; i <33 ; i++) {
                final int te = i;
                es.execute(()->{
                    zl.mp();
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            es.shutdown();
        }
       /* for(int i=0;i<3;i++){
            new Thread(()->  {
                for (int j = 0; j <33; j++) {
                    zl.mp(j);
                }
            },String.valueOf(i)).start();
        }*/
    }
}
