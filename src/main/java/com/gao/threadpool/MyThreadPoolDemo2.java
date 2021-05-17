package com.gao.threadpool;

import java.util.concurrent.*;

/**
 * 手写线程池
 */
public class MyThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService threadPool  = new ThreadPoolExecutor(2, 5, 1L, TimeUnit.SECONDS
                , new LinkedBlockingQueue<Runnable>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 0; i < 10; i++) {
                //threadPool.execute(()->{
                //    System.out.println(Thread.currentThread().getName()+"\t 办理");
                //});
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
