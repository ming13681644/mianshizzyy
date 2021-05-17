package com.gao.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CancalThread {
    public static void main(String[] args) {
        Task task = new Task();
        ExecutorService threadPool = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                try {
                    task.increment();
                    task.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}


class Task {
    private int number = 0;
    private int i = 5;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Condition condition2 = lock.newCondition();

    //����
    public void increment() throws Exception {
        lock.lock();
        try {
            //�ж�
            while (number != 0) {
                condition.await();
            }
            //�ɻ�
            number++;
            i--;
            if (i == 0) {
                throw new Exception("��������");
            }
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //֪ͨ����
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
            condition2.await();
        } finally {
            lock.unlock();
        }
    }

    //����
    public void decrement() {
        lock.lock();
        try {
            //�ж�
            while (number == 0) {
                condition2.await();
            }
            //�ɻ�
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //֪ͨ����
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
