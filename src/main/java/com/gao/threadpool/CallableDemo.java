package com.gao.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * callable与runnable接口区别
 * 		1.callable 	有返回值 	可以抛异常 	还有泛型  	实现call方法	调用:	FutureTask<Integer> futureTask=new FutureTask(new Thread());   Thread thread = new Thread(futureTask); thread.start();
 * 		2.runnable 										实现run方法   	调用:   Thread thread1=new Thread(new MyThread());  thread1.start();
 */
class MyThread implements Runnable {

    public void run() {
        System.out.println("1111111");
    }
}

/**
 *
 */
class MyThread2 implements Callable<Integer> {

    public Integer call() throws Exception {
        System.out.println("0000000000");
        //模拟执行业务逻辑
        Thread.sleep(1000);
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> future = new FutureTask(new MyThread2());
        Thread thread = new Thread(future);
        thread.start();
//       while (!future.isDone()){
//
//       }

        int i=1000;
        System.out.println(i+future.get());
    }
}
