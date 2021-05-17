package com.gao.lock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class H2O2 {


}

class H2O3 {
    public static Semaphore HSema = new Semaphore(2);
    public static Semaphore OSema = new Semaphore(1);
    public CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public H2O3() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        HSema.acquire(2);
        try {
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HSema.release();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        OSema.acquire(1);
        try {
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            OSema.release();
        }

    }



}
