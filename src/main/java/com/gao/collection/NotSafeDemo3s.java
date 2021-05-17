package com.gao.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/*
ArrayList 的类型  Object[] 在内存中占用连续的内存空间   初始大小:10  扩容：原值的一半 再次扩容 15/2向上取整+15 使用方法Arrays.copyof
  线程不安全
HashSet 底层数据结构：HashMap(key，PRESENT)   线程不安全   元素不能重复  去重的一个方法 new HashSet(list);
HsahMap初始大小 16 扩容大小 在负载打到0.75时会自动扩容 一倍  可以存null的键值  键不可以重复会覆盖 值可以重复
 */
public class NotSafeDemo3s {
    public static void main(String[] args) {
        notSafeMap();
    }

    /**
     * 解决方案
     * ConcurrentHashMap()
     */
    private static void notSafeMap() {
        Map map = new ConcurrentHashMap();
        Map hashmap=new HashMap();
        for (int i = 1; i <= 30; i++) {

            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 解决方案
     * CopyOnWriteArrayList() 安全
     */
    private static void setNotSafe() {
        Set set = new CopyOnWriteArraySet();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 集合类不安全（如何写一个验证Arraylist是否安全）
     * 1.故障现象
     * java.util.ConcurrentModificationException    同时修改异常
     * 2.导致原因   高并发
     * 3.解决方法
     * 3.1 Vector  他是线程安全的
     * 3.2 Collections.synchronizedList()
     * 3.3 new CopyOnWriteArrayList() 读时复制   线程安全   Object类型  在add方法上加锁
     * 4.优化建议
     */
    private static void listNotSafe() {
        List<Object> objects = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList();
        Map map = new HashMap();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}