package com.gao.lock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁是指两个或两个以上的线程在执行中,因竞争资源而导致的互相等待现象
 * 若无外力干涉则他们将无法推进下去
 * 排查 使用jps/jstack
 * 直接在idea的控制台使用即可
 * "Thread-1":
 *         at com.gao.lock.DeadLockDemo.run(DeadLockDemo.java:29)
 *         - waiting to lock <0x000000076b900950> (a java.lang.String)
 *         - locked <0x000000076b900988> (a java.lang.String)
 * "Thread-0":
 *         at com.gao.lock.DeadLockDemo.run(DeadLockDemo.java:29)
 *         - waiting to lock <0x000000076b900988> (a java.lang.String)
 *         - locked <0x000000076b900950> (a java.lang.String)
 *
 *Found 1 deadlock.
 */
public class DeadLockDemo implements Runnable {
    private String o1;
    private String o2;

    public DeadLockDemo(String o1, String o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized (o1) {
            System.out.println(Thread.currentThread().getName() + "我将" + o1 + " 锁住了");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "我锁住了+" + o2 + " ");
            }
        }
    }

    public static void main(String[] args) {
        String o1 = "LockA";
        String o2 = "LockB";

        new Thread(new DeadLockDemo(o1, o2)).start();
        new Thread(new DeadLockDemo(o2, o1)).start();
    }
}
