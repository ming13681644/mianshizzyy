package com.gao.lock;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch();
 * 齐楚燕韩赵魏秦版
 * 枚举的使用
 */
public class MyCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "  被灭了");
                countDownLatch.countDown();
            }, CountryEnum.foreachCountryEnum(i).getCountry()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " 关门,谁也走不了");
    }

}
