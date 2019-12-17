package com.atguigu.xyh1.demo1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程之间按顺序调用，实现A先干，B次之，C最后
 * 三个线程启动，A5次，B10次，C15次，
 * 循环10轮
 * 1、高内聚低耦合提前下，线程操作资源类
 * 2、判断，干活，通知
 * 3、多线程交互中，必须要防止多线程的虚假唤醒，用while替换if
 */
/*class forFor{
    public int m;
    public void For(String str){
        while (str.equals("A")){
            m = 5;
        }
        for (int i = 0; i <m ; i++) {
            System.out.println(str+"**"+i);
        }
    }
}*/
class shareResource{
    private int flag = 1;//1:A 2:B 3:C
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    //forFor forfor = new forFor();
    /*public int m;
    public void For(String str){
        while (str.equals("A")){
            m = 5;
        }
        for (int i = 0; i <m ; i++) {
            System.out.println(str+"**"+i);
        }
    }*/
    public void print5(){
        lock.lock();
        try
        {
            //1.判断
            while (flag!=1){
                c1.await();
            }
            //2.干活
           /* String str = Thread.currentThread().getName();
            forfor.For(str);*/
            for (int i = 0; i <5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"**"+i);
            }
            //通知
            flag=2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try
        {
            //1.判断
            while (flag!=2){
                try {
                    c2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //2.干活
            for (int i = 0; i <10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"**"+i);
            }
            //通知
            flag=3;
            c3.signal();
        }finally {
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try
        {
            //1.判断
            while (flag!=3){
                c3.await();
            }
            //2.干活
            for (int i = 0; i <15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"**"+i);
            }
            //通知
            flag=1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ThreadOrderAccess {
    public static void main(String[] args) {
        shareResource shar = new shareResource();
         new Thread(()->  {
             for (int i = 0; i <10 ; i++) {
                 try
                 {
                     shar.print5();
                 }catch (Exception e){
                     e.printStackTrace();
                 }
             }
          },"A").start();
        new Thread(()->  {
            for (int i = 0; i <10 ; i++) {
                try
                {
                    shar.print10();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->  {
            for (int i = 0; i <10 ; i++) {
                try
                {
                    shar.print15();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}
