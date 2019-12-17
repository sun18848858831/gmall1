package com.atguigu.xyh1.demo1;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirConditioner{ //资源类
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void  increment() throws InterruptedException {
        lock.lock();
        try
        {
            //判断
            while (number != 0){
                //this.wait();
                condition.await();
            }
            //干活
            ++number;
            System.out.println(Thread.currentThread().getName()+"："+number);
            //通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //this.notifyAll();
           lock.unlock();
        }
    }
    public void  decrement() throws InterruptedException {
        lock.lock();
        try
        {
            //判断
            while (number == 0){
                //this.wait();
                condition.await();
            }
            //干活
            --number;
            System.out.println(Thread.currentThread().getName()+"："+number);
            //通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //this.notifyAll();
            lock.unlock();
        }
    }
    /*public synchronized void  increment() throws InterruptedException {
        //判断
        if (number != 0){
            this.wait();
        }
        //干活
        ++number;
        System.out.println(Thread.currentThread().getName()+"："+number);
        //通知
        this.notifyAll();
    }
    public  synchronized void  decrement() throws InterruptedException {
        if (number == 0){
            this.wait();
        }
        --number;
        System.out.println(Thread.currentThread().getName()+"："+number);
        //通知
        this.notifyAll();
    }*/

}
public class Test {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
         new Thread(()->  {
             for (int i = 0; i <10 ; i++) {
                 try {
                     Thread.sleep(200);
                     airConditioner.increment();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
          },"A").start();
        new Thread(()->  {
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(300);
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->  {
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(400);
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->  {
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(500);
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }

}
