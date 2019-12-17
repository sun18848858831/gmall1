package com.atguigu.xyh1.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class xCc {
    public static void main(String[] args) {
        Ps ps = new Ps();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
            try
            {
                for (int i = 0; i <33 ; i++) {
                    final int te = i;
                    threadPool.execute(()->{ps.ff(te);});
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                threadPool.shutdown();
            }
    }
}
class Ps{
    private int count = 30;
    private Lock lock = new ReentrantLock();
    public void  ff(int i){  // synchronized
        lock.lock();
        try
        {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName()+"卖出"+(++i)+"张票，"+"剩余"+(--count)+"张票");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}