package com.gao.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目:多线程之间按照顺序调用,实现a,b,c三个线程启动 要求如下 aa打印5次,bb打印10次,cc打印15次
 * 十轮
 */
public class LockDemo2 {
    public static void main(String[] args) {
        Data data = new Data();
        ExecutorService threadPool = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                try {
                   data.print1();
                   data.print3();
                   data.print5();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

class Data {
    private int num = 1;   //标志位
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print1() throws InterruptedException {
        lock.lock();
        try {
            while (num != 1) {
                condition1.await();
            }
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + "aa");
            }
            num=2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print3() throws InterruptedException {
        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            for (int i = 0; i <3; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + "bb");
            }
            num=3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print5() throws InterruptedException {
        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            for (int i = 0; i <5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + "cc");
            }
            num=1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}