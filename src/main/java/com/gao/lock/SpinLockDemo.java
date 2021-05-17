package com.gao.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写自旋锁(不可重入锁)
 * 循环比较知道成功为止,没有wait阻塞
 */
public class SpinLockDemo {
    // 线程之间上锁,因此使用的泛型是线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 不可重入锁,在使用的时候如果锁了两次(模拟重入,递归),则一次unlock就可以将这个锁释放,因此该锁为不可重入锁
     */
    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+" Lock");
        do {

        } while (!atomicReference.compareAndSet(null, thread));
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName()+" UnLock");
    }

    public static void main(String[] args) {
        // 可以换一个别的例子 两个方法分别使用这个锁
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();  //t1
        },"t1").start();
        //1s后启动t2 线程 保证t1 线加锁结束
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //t2
        new Thread(() -> {
            //获取锁成功  需要等待t1 将锁释放 t2释放锁的方法将进入自旋: atomicReference.compareAndSet(thread, null);
            spinLockDemo.lock();

            spinLockDemo.unlock();  //需要等待t1 线程释放锁 t2 才会释放
        },"t2").start();
    }
}
