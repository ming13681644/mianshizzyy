package com.gao.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * countDownLatch �ǲ�ͣ�ļ�   CyclicBarrier���ڼ� ֱ���ﵽ�趨�Ĳ�������ִ��runnable����
 */
public class MyCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,new Dragon());
        for (int i = 1; i <10 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                try {
                    //ִ�� parties ��֮�� ,��ȥִ��cyclicBarrier�ж����runnable ����
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
        System.out.println("�ٻ�����");
    }
}
