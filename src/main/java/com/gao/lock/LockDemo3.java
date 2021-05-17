package com.gao.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 题目:一个初始值为0的变量,两个线程对其交替操作,一个加1一个减1 五轮
 * 1.线程,操作,资源类
 * 2.判断,干活,通知
 * 3.防止虚假唤醒
 */
public class LockDemo3 {
    public static void main(String[] args) {
        ShareData3 shareData = new ShareData3();
        ExecutorService threadPool = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                try {
                    shareData.increment1();
                    shareData.decrement1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

//资源类
class ShareData3 {
    private int number = 0;


    //增加
    public void increment() throws Exception {
        synchronized (this) {
            while (number != 0) {
                wait();
            }
            number++;
            System.out.println(number);
            notifyAll();
        }
    }

    public synchronized void increment1() throws Exception {
        while (number != 0) {
            wait();
        }
        number++;
        System.out.println(number);
        notifyAll();
    }

    //减少
    public void decrement() throws Exception {
        synchronized (this) {
            while (number == 0) {
                wait();
            }
            number--;
            System.out.println(number);
            notifyAll();
        }
    }

    public synchronized void decrement1() throws Exception {
        while (number == 0) {
            wait();
        }
        number--;
        System.out.println(number);
        notifyAll();
    }
}
