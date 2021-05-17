package com.gao.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ���߳�ͬʱ��ȡһ����Դû������,���Կ����ڴ˵Ľ��������ȷ�Ϊ��д��
 * ����
 * �����һ���߳��ڽ���д����,��ô���еĶ�,д�̶߳��ᱻ����
 * С�ܽ�
 * ��-���ܹ���
 * ��-д���ܹ���
 * д-д���ܹ���
 *
 * д�̶߳�ռ��Դ ��д��������֮����ִ�ж�����,������������֤˳��
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache<String, String> myCache = new MyCache<>();
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                try {
                    myCache.add(tempInt + "", tempInt + "");
                    myCache.get(tempInt + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, i + "").start();
        }
    }
}

/**
 * �������Ҫ�пɼ���
 */
class MyCache<K, V> {

    private volatile Map<K, V> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * д����: ԭ��+��ռ(��)
     */
    public void add(K key, V value) throws InterruptedException {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " ����д��");
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " д�����");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public V get(String key) throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " ���ڶ�ȡ");
            TimeUnit.MILLISECONDS.sleep(300);
            V v = map.get(key);
            System.out.println(Thread.currentThread().getName() + " ��ȡ���" + v);
            return v;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void clear() {
        map.clear();
    }
}