package com.gao.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平/非公平/递归/可重入/自旋锁
 */
public class EveryLock {
    volatile int n;

    public void add() {
        n++;
    }

    public static void main(String[] args) {
        // 即可以是公平也可以是非公平   默认非公平
        //公平: 先来后到  非公平 :大家一起抢锁
        Lock lock = new ReentrantLock(true);

    }

}
