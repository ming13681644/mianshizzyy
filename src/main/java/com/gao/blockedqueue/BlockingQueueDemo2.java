package com.gao.blockedqueue;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile/CAS/atomicInteger/BlockingQueue/�߳̽���/ԭ������
 * ����
 */
public class BlockingQueueDemo2 {
    public static void main(String[] args)   {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(3));
        ExecutorService threadPool = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS
                , new LinkedBlockingQueue<Runnable>());

        threadPool.execute(() -> {
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        threadPool.execute(() -> {
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myResource.stop();
        //threadPool.shutdown();
    }


}

class MyResource {
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    //Ϊ������������������ ����ʹ��null  ����set()
    private BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        //�Ų�ʹ�õ���ʲôbolckQueue
//        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while (flag) {
            data = atomicInteger.getAndAdd(5) + "";
            retValue = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t �������" + data + "�ɹ�");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t �������" + data + "ʧ��");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("flag = flase ����ֹͣ");
    }

    public void myConsumer() throws Exception {
        String result = null;
        while (flag) {
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + "\t ���Ѷ���" + result + "�ɹ�");
        }
    }

    public void stop() {
        this.flag = false;
    }

}