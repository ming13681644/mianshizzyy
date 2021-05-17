package com.gao.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlterancePrint {

    public static void main(String[] args) {
        //多线程操作同一个资源类
        Print print = new Print();
        ExecutorService threadPool = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                try {
                    print.printWord();
                    print.printnum();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

class Print {
    public int num = 0;
    public Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public int i = 0;
    public char a = 'a';

    public void printWord() {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            System.out.println(a++);
            num++;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printnum() {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            System.out.println(i++);
            num--;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}