package com.atguigu.xyh1.demo1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class JanJ{
    private int count = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void jj(){
        lock.lock();
        try
        {
           if(count!=0){
               condition.await();
           }
            ++count;
            System.out.println(Thread.currentThread().getName()+count);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void gg(){
        lock.lock();
        try
        {
            if (count==0){
                condition.await();
            }
            --count;
            System.out.println(Thread.currentThread().getName()+count);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class Test1 {
    public static void main(String[] args) {
        JanJ janJ = new JanJ();
         new Thread(()->  {
             for (int i = 0; i <10 ; i++) {
                 try {
                     Thread.sleep(200);
                     janJ.jj();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }

             }
          },"A").start();
        new Thread(()->  {
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(200);
                    janJ.gg();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"B").start();
        new Thread(()->  {
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(200);
                    janJ.jj();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"C").start();
        new Thread(()->  {
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(200);
                    janJ.gg();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"D").start();
    }
}
