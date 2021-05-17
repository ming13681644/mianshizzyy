package com.gao.lock;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch();
 * ����ຫ��κ�ذ�
 * ö�ٵ�ʹ��
 */
public class MyCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "  ������");
                countDownLatch.countDown();
            }, CountryEnum.foreachCountryEnum(i).getCountry()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " ����,˭Ҳ�߲���");
    }

}
