package com.bins.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by songrongbin on 2016/10/8.
 */
public class ScheduledThreadPoolExecutorTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+ ":" + "delay 3 seconds");
            }
        }, 10, TimeUnit.SECONDS);
    }
}
