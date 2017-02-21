package com.bins.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by songrongbin on 2016/10/8.
 */
public class NoCachedThreadPoolExecutorTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Thread(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+ ":" + index);
                }
            }).start();
        }
    }
}
