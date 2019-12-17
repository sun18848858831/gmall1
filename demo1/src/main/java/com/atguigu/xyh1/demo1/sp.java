package com.atguigu.xyh1.demo1;



import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class sp {
    public static void main(String[] args) {
        Sx sx = new Sx();
        new Thread(()->  { for (int i = 0; i <33 ; i++) sx.ff(i);},"A").start();
        new Thread(()-> { for (int i = 0; i <33 ; i++) sx.ff(i);},"B").start();
        new Thread(()-> { for (int i = 0; i <33 ; i++) sx.ff(i);},"C").start();
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <33 ; i++) {
                    sx.ff(i);
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <33 ; i++) {
                    sx.ff(i);
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <33 ; i++) {
                    sx.ff(i);
                }
            }
        },"C").start();*/
    }
}

class Sx {

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
   /*public synchronized void  ff(int i){
        if (count > 0) {
            System.out.println(Thread.currentThread().getName()+"卖出"+(++i)+"张票，"+"剩余"+(--count)+"张票");
        }
    }*/
}