package com.atguigu.xyh1.demo1;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class NotSafeDemo {
    public static void main(String[] args) {
        Map<String,String> map =new ConcurrentHashMap<>();//new HashMap<>();
        for(int i=0;i<30;i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 6));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
        /*Set<String> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());//new HashSet<>();
        new HashSet<>().add("a");
        for(int i=0;i<30;i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }*/
       /* List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new Vector<>();//new ArrayList<>();
        for(int i=0;i<30;i++){
            new Thread(()->  {
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            },String.valueOf(i)).start();
        }*/

    }
}
