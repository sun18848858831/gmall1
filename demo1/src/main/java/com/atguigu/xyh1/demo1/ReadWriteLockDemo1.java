package com.atguigu.xyh1.demo1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，
 * 读取共享资源应该可以同步
 * 但是，如果有一个线程想去写共czxcxzv享资源，就不应该再有其他线程可以
 * 对该资源进行读或写
 * 读-读能共存，读-写不能共存，写-写不能共存
 *
 * 1.不加锁 乱写error，并发读可以
 * 2.加lock 写ok，但是并发读下降
 * 3.加ReentrantReadWriteLock，写唯一，读并发高性能
 */
class WandR{
    private volatile Map<String,String> map = new HashMap<>();
    private Lock lock = new ReentrantLock();
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    public void put(String k,String v){
        lock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"写操作开始");
            map.put(k,v);
            System.out.println(Thread.currentThread().getName()+"写操作结束");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void get(String k){
        lock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"读操作开始");
            String te = map.get(k);
            System.out.println(Thread.currentThread().getName()+"读操作结束"+te);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    /*public void put(String k,String v){
        rwl.writeLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"写操作开始");
            map.put(k,v);
            System.out.println(Thread.currentThread().getName()+"写操作结束");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwl.writeLock().unlock();
        }
    }
    public void get(String k){
        rwl.readLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"读操作开始");
            String te = map.get(k);
            System.out.println(Thread.currentThread().getName()+"读操作结束"+te);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock();
        }
    }*/
}
public class ReadWriteLockDemo1 {
    public static void main(String[] args) {
        WandR wr = new WandR();
        for(int i=0;i<100;i++){
            final int te = i;
            new Thread(()->  {
                wr.put(te+"",te+"");
            },String.valueOf(i)).start();
        }
        for(int i=0;i<100;i++){
            final int te = i;
            new Thread(()->  {
                wr.get(te+"");
            },String.valueOf(i)).start();
        }
    }
}
