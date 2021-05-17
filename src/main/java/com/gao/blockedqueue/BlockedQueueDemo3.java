package com.gao.blockedqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockedQueueDemo3 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(3);
        BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>();
        arrayBlockingQueue.add("aaa");
        arrayBlockingQueue.add("bbb");
        arrayBlockingQueue.add("ccc");
        //超时时间
        System.out.println(arrayBlockingQueue.offer("ddd",10000, TimeUnit.MILLISECONDS));
        //头元素
        System.out.println(arrayBlockingQueue.element());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        //加元素
        System.out.println(arrayBlockingQueue.offer("ccc"));
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());



    }
}
