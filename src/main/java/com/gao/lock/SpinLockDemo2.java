package com.gao.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写自旋锁(不可重入锁)
 * 循环比较知道成功为止,没有wait阻塞
 */
public class SpinLockDemo2 {
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
       new Thread(new MyThread(),"t1").start();
    }
}
class MyThread implements Runnable{
    SpinLockDemo2 lock=new SpinLockDemo2();
    @Override
    public void run() {
        get();
    }
    public void get(){
        System.out.println(Thread.currentThread().getName() +"  ---get");
        lock.lock();
        add();
        //无法释放对应的锁 进入自旋 死锁
        lock.unlock();
    }
    public void add(){
        System.out.println(Thread.currentThread().getName() +"  ---add");
        lock.lock();
        //无法释放对应的锁 进入自旋 死锁
        lock.unlock();
    }
}
