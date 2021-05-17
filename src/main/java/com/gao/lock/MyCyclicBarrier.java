package com.gao.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * countDownLatch 是不停的减   CyclicBarrier是在加 直到达到设定的参数才能执行runnable方法
 */
public class MyCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,new Dragon());
        for (int i = 1; i <10 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                try {
                    //执行 parties 次之后 ,会去执行cyclicBarrier中定义的runnable 方法
                    System.out.println(tempInt);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
class Dragon implements Runnable{
    @Override
    public void run() {
        System.out.println("召唤神龙");
    }
}
