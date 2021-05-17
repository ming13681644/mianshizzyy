package com.gao.sofoom;

/**
 * 1.应用程序创建了太多的线程,超过系统承载数量;
 * 2.服务器不允许创建那么多的线程linux默认允许一个进程可以创建1024个线程超过数量会报OutOfMemoryError: Unable to create a native thread
 */
public class UnableCreateNativeThread {
    public static void main(String[] args) {
        for (int i = 0;  ; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
