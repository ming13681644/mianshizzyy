package com.gao.jvm;

import java.lang.ref.SoftReference;

/**
 * 内存不够就会回收 尽量避免发生OOM
 */
public class SoftReferenceDemo {
    //内存够就保留
    public static void softRefMemoryEnough() {
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o);
        System.out.println(o);
        System.out.println(softReference.get());
        o = null;
        System.gc();
        System.out.println("--------执行垃圾回收之后---------");
        System.out.println(o);
        System.out.println(softReference.get());
    }

    public static void softRefMemoryNotEnough() {
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o);
        System.out.println(o);
        System.out.println(softReference.get());
        o = null;
        System.gc();
        System.out.println("--------执行垃圾回收之后---------");
        try {
            //-Xms5m -Xmx5m
            byte[] bytes = new byte[10 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o);
            System.out.println(softReference.get());
        }


    }

    public static void main(String[] args) {
       softRefMemoryEnough();
        //softRefMemoryNotEnough();
    }
}
