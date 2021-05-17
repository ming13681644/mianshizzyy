package com.gao.volatileDemo;

/**
 * volatile 禁止指令重排
 * 处理器再执行指令重排期间一定要考虑数据的依赖性
 * 多线程环境中,指令交替执行,由于编译器优化重排的存在,两个线程中使用的变量能否保持一直是无法确定的,结果无法预算
 */
public class VolatileDemo3 {
    public static void main(String[] args) {
        ReSortSeqDemo reSortSeqDemo = new ReSortSeqDemo();
    }
}

class ReSortSeqDemo {
    int a = 0;
    boolean flag = false;

    /**
     * 假设再此进行了指令重排 执行顺序变为了
     *   1.flag = true;
     *   2.a = 1;
     *   当时当1执行结束之后并未执行2的时候 method2执行
     */
    public void method1() {
        a = 1;
        flag = true;
    }

    public void method2() {
        if (flag) {
            a += 5;
            System.out.println("reSet Value: " + a);
        }
    }
}
