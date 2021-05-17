package com.gao.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * compare and swape
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        //比较期望值是否一致 要是一致的话 会更新值 否则不会做任何操作
        atomicInteger.compareAndSet(0, 20);
        System.out.println(atomicInteger.get());
        atomicInteger.compareAndSet(0, 1024);
        System.out.println(atomicInteger.get());
    }
}
