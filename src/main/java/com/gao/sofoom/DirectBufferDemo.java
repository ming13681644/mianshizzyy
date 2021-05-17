package com.gao.sofoom;

import java.nio.ByteBuffer;

/**
 * 用NIO程序回经常使用ByteBuffer来读取或者写入这是一种基于Channel 还有buffer 的I/O方式,他可以使用Native函数直接分配堆外内存然后通过一个存储在java堆中的
 * 		DirectByteBuffer对象作为这块内存的引用进行操作,这样在一些场景中提高了性能,因为避免了堆在Native堆中来回复制数据
 * 	但是不断分配本地内存而堆内存很少使用那么jvm不会GC DirectBuffer对象不会被回收,这时堆内存丰富而本地内存吃紧,再次尝试分配本地内存的时候就会出现OOM
 * -Xms2m -Xmx2m  -XX:MaxDirectMemorySize=5m
 */
public class DirectBufferDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory大小:" + sun.misc.VM.maxDirectMemory() / (double) (1024 * 1024) + "MB");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
