package com.atguigu.xyh1.demo1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception{
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
       /* blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("1111111111111111");*/
        //blockingQueue.put("d");
        //System.out.println("2222222222222222");
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.offer("a",3, TimeUnit.SECONDS));
        System.out.println(blockingQueue.peek());
        /*System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());*/
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll(3,TimeUnit.SECONDS));
    }
}
