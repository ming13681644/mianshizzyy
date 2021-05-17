package com.gao.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多线程同时读取一个资源没有问题,所以可以在此的将锁的粒度分为读写锁
 * 但是
 * 如果有一个线程在进行写操作,那么所有的读,写线程都会被阻塞
 * 小总结
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 *
 * 写线程独占资源 当写操作结束之后在执行读操作,读操作共享不保证顺序
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
 * 缓存必须要有可见性
 */
class MyCache<K, V> {

    private volatile Map<K, V> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 写操作: 原子+独占(锁)
     */
    public void add(K key, V value) throws InterruptedException {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写入");
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public V get(String key) throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取");
            TimeUnit.MILLISECONDS.sleep(300);
            V v = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取完成" + v);
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