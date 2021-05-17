package com.gao.lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class ThreadDemo {
    public static void main(String[] args) {
        ThreadFactory myThreadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "测试多线程-%d" + r.hashCode());
            }
        };
        ExecutorService threadPool = new ThreadPoolExecutor(8, 100, 1L, TimeUnit.SECONDS
                , new ArrayBlockingQueue<Runnable>(500), myThreadFactory);
        for (int i = 0; i < 500; i++) {
            threadPool.execute(() -> {
                System.out.println(DateUtils.parse("2121-04-22"));
            });
        }
        threadPool.shutdown();
    }
}

class DateUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static Date parse(String s) {
        try {
            return simpleDateFormat.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
