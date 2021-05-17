package com.gao.jvm;

import java.lang.ref.SoftReference;

/**
 * �ڴ治���ͻ���� �������ⷢ��OOM
 */
public class SoftReferenceDemo {
    //�ڴ湻�ͱ���
    public static void softRefMemoryEnough() {
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o);
        System.out.println(o);
        System.out.println(softReference.get());
        o = null;
        System.gc();
        System.out.println("--------ִ����������֮��---------");
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
        System.out.println("--------ִ����������֮��---------");
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
