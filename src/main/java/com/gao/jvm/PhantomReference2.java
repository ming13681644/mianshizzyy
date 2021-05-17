package com.gao.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 类是后置通知
 */
public class PhantomReference2 {
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference phantomReference = new PhantomReference<Object>(o1, referenceQueue);
        System.out.println(o1);
        System.out.println(phantomReference.get()); //每次调用都是null
        System.out.println(referenceQueue.poll());
        System.out.println("-------------GC-----------");
        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(phantomReference.get()); //每次调用都是null
        System.out.println(referenceQueue.poll());
    }
}
