package com.atguigu.xyh1.demo1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class shiYan {
    public static void main(String[] args) {
        Collection coll = new ArrayList();
        coll.add("aaa");
        coll.add(12);
        coll.add(3.4);
        coll.add("aaa");
        System.out.println(coll);
        Iterator iter = coll.iterator();

//            long maxMemory = Runtime.getRuntime().maxMemory() ;//返回 Java 虚拟机试图使用的最大内存量。
//            long totalMemory = Runtime.getRuntime().totalMemory() ;//返回 Java 虚拟机中的内存总量。
//            System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 / 1024) + "MB");
//            System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
   /* String str = "www.haopanya.com";
    while (true){
        str += str + new Random().nextInt(8888)+new Random().nextInt(8888899);
    }*/
    }
}
