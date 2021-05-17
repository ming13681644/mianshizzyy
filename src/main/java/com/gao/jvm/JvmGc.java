package com.gao.jvm;

/**
 * -Xss 初始栈空间
 */
public class JvmGc {
    public static void main(String[] args) throws InterruptedException {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        //初始堆空间  1/64 计算机内存
        System.out.println("-Xms : " + totalMemory / (1024 * 1024));
        //最大堆空间     1/4计算机内存
        System.out.println("-Xmx : " + maxMemory / (1024 * 1024));
        System.out.println(totalMemory);
        System.out.println(maxMemory);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
