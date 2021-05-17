package com.gao.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ABA问题:
 * 1.t1,t2 两个线程同时取到主内存的值为A,并将其保存在工作内存中
 * 2.t2线程执行较快,将A的值修改为B,此时T2工作内存中的值为B,主内存为B ,
 * 3.但是t2线程又将主工作内存中的值修改为了A 主内存A t2 执行结束
 * 4.t1在工作的时候发现期望值为A,主内存中的值为A ,并未发现有人将值修改为B
 * 首尾一致 但是中间的值被修改过了  只管头尾
 */
public class CASABA {
    private static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);

    public static void main(String[] args) {
        new Thread(() -> {
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();
        new Thread(() -> {
            //保证t1线程完成一次
            try {TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            atomicReference.compareAndSet(100,2019);
            System.out.println(atomicReference.get());
        },"t2").start();
    }
}
