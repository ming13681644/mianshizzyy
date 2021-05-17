package com.gao.sofoom;

/**
 * -Xms2m -Xmx2m -XX:+PrintGCDetails
 * -Xmx5m -Xmx5m -XX:+PrintGCDetails -XX:UseSerialGC
 *
 * -Xmx5m -Xmx5m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags
 * -Xmx5m -Xmx5m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags
 * -Xmx5m -Xmx5m -XX:+PrintGCDetails -XX:+UseParallelGC -XX:+PrintCommandLineFlags
 * -Xmx5m -Xmx5m -XX:+PrintGCDetails -XX:+UseG1GC -XX:+PrintCommandLineFlags
 */
public class OutOfMemeryDemo2 {
    public static void main(String[] args) {
        try {
            String s = "gaomingyan";
            while (true) {
                //底层有个Stringbuilder 对象
                s += s + new String("bbb");
                s.intern();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
