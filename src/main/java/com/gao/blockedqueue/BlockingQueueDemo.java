package com.gao.blockedqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ArrayBlockingQueue 是一个基于数组数据结构的有界阻塞队列,按照先进先出的原则对元素进行排序
 * LinkedBlockingQueue 是一个基于链表的数据结构的有界阻塞队列,按照先进先出排序元素,吞吐量要高于ArrayBlockingQueue
 * SynchorizedQueue 一个不存储元素的阻塞队列.每个插入操作必须等到另一个线程调用移除操作 否则插入操作一直处于阻塞状态,  不消费不生产
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1 ");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "\t put 2 ");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t put 3 ");
                blockingQueue.put("3");
            } catch (Exception e) {
                e.printStackTrace();

            }
        }, "aa").start();

        new Thread(() -> {
            for (int i = 0; i <3 ; i++) {
                try {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(blockingQueue.take());
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

        }, "aa").start();


    }

}
