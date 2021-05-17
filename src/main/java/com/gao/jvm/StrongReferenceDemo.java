package com.gao.jvm;

/**
 * 强引用
 * 当进行垃圾回收时,对于强引用的对象就算是出现了OOM也不会对该对象进行回收
 * 即使该对象永远都不使用也不会被回收因此强引用是造成java内存泄漏的主要原因之一
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object(); //默认强引用
        Object o2 = o1; //引用赋值
        o1 = null;  //清空o1
        System.gc();
        System.out.println(o2);
    }
}
