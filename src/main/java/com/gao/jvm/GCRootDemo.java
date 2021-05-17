package com.gao.jvm;

/**
 * GCRoot
 * 1.虚拟机栈中引用的对象
 * 2.方法区中类静态属性引用的对象
 * 3.方法区中常量引用的对象
 * 4.本地方法栈中引用的对象
 */
public class GCRootDemo {
    private byte[] byteArray = new byte[1024 * 1024 * 2];
    //方法区中类静态属性引用的对象
    private static Object object1 = new Object();
    //方法区中常量引用的对象
    private static final Object object2 = new Object();

    public void method1() {
        //栈中gcRootDemo 指向new GCRootDemo()
        GCRootDemo gcRootDemo = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

}
