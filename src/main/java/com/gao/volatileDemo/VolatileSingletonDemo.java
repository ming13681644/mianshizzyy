package com.gao.volatileDemo;

public class VolatileSingletonDemo {
    private static volatile VolatileSingletonDemo volatileSingletonDemo;

    //私有化构造方法
    private VolatileSingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "创建成功");
    }

    public static VolatileSingletonDemo newInstance() {
        if (null == volatileSingletonDemo) {
            synchronized (VolatileSingletonDemo.class) {
                if (null == volatileSingletonDemo) {
                    volatileSingletonDemo = new VolatileSingletonDemo();
                }
            }
        }
        return volatileSingletonDemo;
    }

    public static void main(String[] args) {
        VolatileSingletonDemo volatileSingletonDemo = VolatileSingletonDemo.newInstance();
        VolatileSingletonDemo volatileSingletonDemo1 = VolatileSingletonDemo.newInstance();
        System.out.println(volatileSingletonDemo == volatileSingletonDemo1);
        for (int i = 0; i < 10; i++) {
            new Thread(VolatileSingletonDemo::newInstance,"Thread Name").start();
        }
    }
}
