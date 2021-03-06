package com.gao.sofoom;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms2m -Xmx2m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 都是full GC 且回收不了对象
 *
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("新建对象个数 : " + i);
            e.printStackTrace();
        }

    }
}
