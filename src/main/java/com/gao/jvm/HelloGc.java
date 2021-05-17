package com.gao.jvm;

/**
 * 如何查看一个正在运行中的java程序jvm参数是否开启?具体值是多少
 * jps + jinfo
 * jinfo -flag PrintGCDetails <pid>
 *
 */
public class HelloGc {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("helloGc---------");
        //-Xms10m -Xmx10m -XX:+PrintGCDetails
        byte[] bytes = new byte[10* 1024 * 1024];
        /**
         * GC 位置                             GC前/GC后(GC所处的位置大小)  堆前/堆后 (堆大小)
         * [GC (Allocation Failure) [PSYoungGen: 1752K->504K(2560K)] 1752K->711K(9728K), 0.0026099 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         */
       Thread.sleep(Integer.MAX_VALUE);
    }
}
