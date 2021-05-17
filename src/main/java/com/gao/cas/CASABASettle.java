package com.gao.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ʹ�ð汾��,����ֻ����ֵ�Ƚ�
 */
public class CASABASettle {
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t" + "��1�ΰ汾��: " + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t" + "��2�ΰ汾��: " + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t" + "��3�ΰ汾��: " + atomicStampedReference.getStamp());

        }, "t1").start();
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t" + "��1�ΰ汾��: " + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(flag);
            System.out.println(atomicStampedReference.getReference());
            //����汾���ǵ�ǰ���°汾��
            System.out.println(Thread.currentThread().getName() + "\t" + "��2�ΰ汾��: " + atomicStampedReference.getStamp());
        }, "t2").start();
    }


}
