package com.gao.volatileDemo;

import java.util.concurrent.TimeUnit;

/**
 * 1.保证可见性
 * 2.不保证原子性(sync 保证原子性)
 * 3.禁止指令重排
 * JMM 是一种规范，规范了程序中各个变量的访问方式，JMM规定了所有变量都在主内存中，每个线程还有自动的工作内存
 * ，工作内存存放的是主内存的拷贝，线程的所有操作在工作内存中完成（volatitle仍然是工作内存拷贝）
 * ，不同线程之间无法访问对方工作内存的变量，线程之间传值考主内存完成首先从主内存拷贝一个到自己的工作内存，操作后在放入主内存，不能直接操作主内存中的变量
 * JMM关于同步的规定：
 * 1.线程解锁前，必须把共享变量的值刷新回主内存
 * 2.线程加锁前，必须读取主内存动的最新值到自己的工作内存
 * 3.加锁解锁同一把锁
 * JMM内存可见性: 主内存所有线程都能访问,但每个线程的读写操作必须在工作内存中,操作后在将变量写回到主内存,因此不同的线程之间无法互相访问
 */
public class VolatileDemo {
    /**
     * 验证volatile内存的可见性 主要是通知机制
     *
     * @param args
     */
    public static void main(String[] args) {
        MyData myData = new MyData();
        //3s后 aaa线程 已经将值改为60 再aaa线程的工作内存中 num修改为60 并写回组内存
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + " update num to 60");
        }, "aaa").start();
        //main线程一直再等待num的值什么时候变为0,当没有volatile时 num值的变化不会被main线程感知
        while (myData.num == 0) {
            //main线程等待直到num!=0
            if (myData.num != 0) {
                break;
            }
        }
        System.out.println("执行结束");
    }
}

/**
 * 加入int num=0;num变量之前没有volatile关键字 没有线程可见性
 * vilatile关键字 当有线程修改了num值 其他的线程都会感知到这个值的修改
 */
class MyData {
    volatile int num = 0;

    public void addTo60() {
        this.num = 60;
    }
}
