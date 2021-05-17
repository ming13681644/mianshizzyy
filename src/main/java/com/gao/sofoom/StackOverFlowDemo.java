package com.gao.sofoom;

/**
 * Exception in thread "main" java.lang.StackOverflowError
 * 在jvm运行时的数据区域中有一个java虚拟机栈，当执行java方法时会进行压栈弹栈的操作。在栈中会保存局部变量，操作数栈，方法出口等等。
 * jvm规定了栈的最大深度，当执行时栈的深度大于了规定的深度，就会抛出StackOverflowError错误。
 * 比较常出现的就是循环调用
 *
 *
 * 线程申请的栈 超出了栈所规定的最大深度****
 *
 * 是个Error 虚拟机Error
 */
public class StackOverFlowDemo {
    public static void Foo(){
        Foo();
    }

    public static void main(String[] args) {
        Foo();
    }
}
