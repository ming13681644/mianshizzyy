package com.gao.lock;

/**
 * 1.首先要有两个 公共资源 o1,o2   两个线程,两个静态资源非本类所有,在持有各自的资源时间内尝试持有另一资源的锁
 *  线程1 持有o1 的锁 一段时间  然后尝试持有o2
 *  线程2 持有o2的锁  一段时间  然后尝试持有o1
 *  两个线程造成了线程的死锁
 *  2. ReentrantLock 加锁两次 但是释放锁只释放一次
 */
public class DeadLocK implements Runnable{
    private int flag;
    //必须是静态资源
    private static final Object o1 = new Object();
    private static final Object o2 = new Object();
    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag == 1) {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + " o1");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " o2");
                }
            }
        }
        if (flag == 2) {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " o2");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + " o1");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLocK deadLock1 = new DeadLocK();
        DeadLocK deadLock2 = new DeadLocK();

        deadLock1.setFlag(1);
        Thread thread1= new Thread(deadLock1, "Thread1");
        thread1.start();

        deadLock2.setFlag(2);
        Thread thread2= new Thread(deadLock2, "Thread2");
        thread2.start();
    }
}
