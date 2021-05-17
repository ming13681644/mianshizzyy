package com.gao.sofoom;

/**
 * 对象申请的 空间大于了 堆的最大空间
 */
public class OutOfMemeryDemo {
    public static void main(String[] args) {
        //-Xms10m -Xmx10m
        byte[] bytes = new byte[15 * 1024 * 1024];

    }
}
