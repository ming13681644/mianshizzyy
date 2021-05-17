package com.gao.lock;

/**
 * 1.����Ҫ������ ������Դ o1,o2   �����߳�,������̬��Դ�Ǳ�������,�ڳ��и��Ե���Դʱ���ڳ��Գ�����һ��Դ����
 *  �߳�1 ����o1 ���� һ��ʱ��  Ȼ���Գ���o2
 *  �߳�2 ����o2����  һ��ʱ��  Ȼ���Գ���o1
 *  �����߳�������̵߳�����
 *  2. ReentrantLock �������� �����ͷ���ֻ�ͷ�һ��
 */
public class DeadLocK implements Runnable{
    private int flag;
    //�����Ǿ�̬��Դ
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
