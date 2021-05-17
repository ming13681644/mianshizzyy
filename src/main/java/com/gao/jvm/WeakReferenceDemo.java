package com.gao.jvm;

import java.lang.ref.WeakReference;

/**
 * 弱引用 不管内存够不够 都会回收
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<Object>(o);
        System.out.println(o);
        System.out.println(weakReference.get());
        o = null;
        System.gc();
        System.out.println("------------------");
        System.out.println(weakReference.get());
    }
}
