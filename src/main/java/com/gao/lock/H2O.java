package com.gao.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class H2O {
}

class H2O1 {
    public Lock lock = new ReentrantLock();
    public Condition hCondition = lock.newCondition();
    public Condition oCondition = lock.newCondition();
    public int Hcount = 0;

    public H2O1() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while (Hcount == 2) {
                hCondition.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            Hcount++;
            if (Hcount == 2) {
                oCondition.signal();
            } else {
                hCondition.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            while (Hcount != 2) {
                oCondition.await();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            Hcount=0;
            if (Hcount==0){
                hCondition.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
