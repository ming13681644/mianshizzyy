package com.gao.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 降低资源消耗：通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
 * 提高响应速度：当任务到达时，可以不需要等待线程创建就能立即执行。
 * 提高线程的可管理性：线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，监控和调优。
 * Java中线程是通过Executor框架实现的,该框架中用到了Executor,Executors,ExecutorService,ThreadPoolExecutor这几个类
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //模拟10个任务,每个任务就是一个来自外部的线程
        try {
            for (int i = 0; i < 10; i++) {
                //threadPool.execute(()->{
                //    System.out.println(Thread.currentThread().getName()+"\t 办理");
                //});
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
               threadPool.shutdown();
        }

//        ExecutorService threadPool = Executors.newCachedThreadPool();
//        try {
//            for (int i = 0; i < 20; i++) {
//                threadPool.execute(()->{
//                    System.out.println(Thread.currentThread().getName()+"\t 办理");
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//               threadPool.shutdown();
//        }

    }

}
