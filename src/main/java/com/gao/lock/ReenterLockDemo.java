package com.gao.lock;

/**
 * 可重入锁
 * 指同一线程外层函数获得锁之后,内层递归函数仍然能获取该锁,
 * 在同一线程外层方法获取锁的时候,在进入内层方法直接获取锁
 * <p>
 * 也就是说一个线程可以进入任何一个它已经拥有的锁的同步代码
 *
 *
 * public synchronized void method01(){
 * 当一个线程访问method01时同时也会对method02加锁
 *       method02();
 * }
 * public synchronized void method02(){
 * }
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        /**
         * 11  ---call
         * 11  ---sendMsg
         * 12  ---call
         * 12  ---sendMsg
         */
        new Thread(() -> {
            phone.call();
        }, "t1").start();
        new Thread(() -> {
            phone.call();
        }, "t2").start();
    }
}

class Phone {
    public synchronized void call() {
        System.out.println(Thread.currentThread().getId() + "  ---call");
        //不可重入的话   应该答应别的id
        sendMsg();
    }

    public synchronized void sendMsg() {
        System.out.println(Thread.currentThread().getId() + "  ---sendMsg");
    }

}
