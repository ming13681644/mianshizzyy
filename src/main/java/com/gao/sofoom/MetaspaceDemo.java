package com.gao.sofoom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 8之后使用Metaspace代替永久代
 * MetaSpace存放了一下信息:
 * 1.虚拟机加载的类信息
 * 2.常量池
 * 3.静态变量
 * 4.即时编译后的代码
 * -XX:MetaspaceSize=32m -XX:MaxMetaspaceSize=32m
 */
public class MetaspaceDemo {
    static class OOMTest {

    }

    public static void main(String[] args) {
        try {
            while (true) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invoke(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
