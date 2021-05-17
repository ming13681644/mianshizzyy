package com.gao.jvm;

import java.util.WeakHashMap;

/**
 * key不在引用 则清空map中的值
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        MyWeakHashMap();
    }

    private static void MyWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "value";
        map.put(key, value);
        System.out.println(map);

        key = null;
        System.gc();
        System.out.println(map);


    }

}
