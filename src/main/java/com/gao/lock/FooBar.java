package com.gao.lock;

public class FooBar {
    public static void main(String[] args) {

    }

}

class FooBar1 {
    private int n=20;
    volatile boolean flag = true;

    public FooBar1(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            if (flag) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = false;
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!flag) {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = true;
            }
        }
    }
}
