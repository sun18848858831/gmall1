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
class Lx{
    private int count = 1;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    public void p5(){
        lock.lock();
        try
        {
            while (count!=1){
                condition.await();
            }
            for (int i = 0; i <5 ; i++) {
                System.out.println(Thread.currentThread().getName()+i+"次");
            }
            count = 2;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void p10(){
        lock.lock();
        try
        {
            while (count!=2){
                condition1.await();
            }
            for (int i = 0; i <10 ; i++) {
                System.out.println(Thread.currentThread().getName()+i+"次");
            }
            count = 3;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void p15(){
        lock.lock();
        try
        {
            while (count!=3){
                condition2.await();
            }
            for (int i = 0; i <15 ; i++) {
                System.out.println(Thread.currentThread().getName()+i+"次");
            }
            count = 1;
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //private int n = 5;
   /* public void zq(String m){
        System.out.println(Thread.currentThread().getName());
        if(m.equals(2)){
            n = 10;
        }else if (m.equals(3)){
            n = 15;
        }else if(m.equals(1)){
            n = 5;
        }else {
            return;
        }
        for (int i = 0; i <n ; i++) {
            System.out.println(Thread.currentThread().getName()+"1");
        }
    }*/
}
public class ThreadOrderAccess1 {
    public static void main(String[] args) {
        Lx lx = new Lx();
        new Thread(()->  {
            for (int i = 0; i <10 ; i++) {
                lx.p5();
            }
         },"A").start();
        new Thread(()->  {
            for (int i = 0; i <10 ; i++) {
                lx.p10();
            }
        },"B").start();
        new Thread(()->  {
            for (int i = 0; i <10 ; i++) {
                lx.p15();
            }
        },"C").start();
    }
}
