package com.gao.volatileDemo;

import java.util.concurrent.CountDownLatch;

/**
 * 验证volatile不保证原子性
 * 原子性要么同时成功 要么同时失败
 */
public class VolatileDemo1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20 * 1000);
        MyData1 myData1 = new MyData1();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData1.addPlusPlus();
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        //需要等待线程结束 再输出值
        countDownLatch.await();
        System.out.println(myData1.num);
    }
}

/**
 * 加volatile关键字不保证原子性
 */
class MyData1 {
    volatile int num = 0;

    public void addPlusPlus() {
        //实际上是三个指令1.取值2.加一3.写回主内存  会出现写丢失
        num++;
    }
}

