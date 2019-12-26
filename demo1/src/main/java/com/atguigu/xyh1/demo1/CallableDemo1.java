package com.atguigu.xyh1.demo1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class ShiYan implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("call**************");
        return 1024;
    }
}
public class CallableDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new ShiYan());
        new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());
    }
}
