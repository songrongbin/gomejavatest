package com.bins.threadpool;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExecutorTest {
    public static void main(String[] args) {
        System.out.println(Double.parseDouble(String.valueOf(0)));
        /*
        BigDecimal result = new BigDecimal(1).multiply(new BigDecimal(10)).divide(new BigDecimal(3));
        if(result.intValue() == 0) {
            System.out.println(1);
        } else {
            System.out.println( result.intValue());
        }*/
        /*ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+ ":" + index);
                }
            });
        }*/
    }
}  