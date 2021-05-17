package com.gao.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁(可重入)
 */
public class SpinLockDemo3 {
    private AtomicReference<Thread> owner = new AtomicReference<>();
    private int count = 0;

    public void lock() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            count++;
            return;
        }
        while (!owner.compareAndSet(null, current)) {
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            if (count != 0) {
                count--;
            } else {
                owner.compareAndSet(current, null);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new MyThread2()).start();
    }
}

class MyThread2 implements Runnable {
    SpinLockDemo3 lock = new SpinLockDemo3();

    @Override
    public void run() {
        get();
    }

    public void get() {
        System.out.println(Thread.currentThread().getName() + "  ---get");
        lock.lock();
        add();
        lock.unlock();
    }

    public void add() {
        System.out.println(Thread.currentThread().getName() + "  ---add");
        lock.lock();
        lock.unlock();
    }
}
