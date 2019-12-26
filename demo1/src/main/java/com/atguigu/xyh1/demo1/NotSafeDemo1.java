package com.atguigu.xyh1.demo1;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class NotSafeDemo1 {
    public static void main(String[] args) {
        List list = new Vector();//new CopyOnWriteArrayList();//Collections.synchronizedList(new ArrayList<>());//new ArrayList();
        for(int i=0;i<30;i++){
            new Thread(()->  {
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        /*Set<String> set = Collections.synchronizedSet(new HashSet<>());//new CopyOnWriteArraySet<>();//new HashSet<>();
        for(int i=0;i<30;i++){
            new Thread(()->  {
                set.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(set);
            },String.valueOf(i)).start();
        }*/
        /*Map<String,String> map = new ConcurrentHashMap<>();//new HashMap<>();
        for(int i=0;i<30;i++){
            new Thread(()->  {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,6));
                System.out.println(map);
            },String.valueOf(i)).start();
        }*/
    }
}
