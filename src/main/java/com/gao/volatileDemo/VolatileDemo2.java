package com.gao.volatileDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决原子性
 * 1.加锁sync
 * 2.使用atomic
 */
public class VolatileDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20 * 1000);
        MyData2 myData2 = new MyData2();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData2.addAtomicPlusPlus();
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        //需要等待线程结束 再输出值
        countDownLatch.await();
        System.out.println(myData2.atomicInteger);
    }
}

/**
 * 加volatile关键字不保证原子性
 */
class MyData2 {
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomicPlusPlus() {
        atomicInteger.incrementAndGet();
    }
}
