package com.gao.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 加锁的个数跟解锁的个数一定要相同,否则可能出现死锁
 */
public class ReenterLockDemo02 implements Runnable {
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "---get");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "---get");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReenterLockDemo02 reenterLockDemo02 = new ReenterLockDemo02();
        new Thread(reenterLockDemo02, "t1").start();
        new Thread(reenterLockDemo02, "t2").start();
    }
}
