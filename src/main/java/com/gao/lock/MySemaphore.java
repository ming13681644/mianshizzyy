package com.gao.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * �źű�
 * ��Ҫ���ڶ��������Դ����ʹ��,��һ���ǲ����߳����Ŀ���
 */
public class MySemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();  //ռ����һ��λ��
                    System.out.println(Thread.currentThread().getName()+" ռ��λ��");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+" �뿪λ��");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
