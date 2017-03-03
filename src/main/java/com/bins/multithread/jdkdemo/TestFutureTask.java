package com.bins.multithread.jdkdemo;

import java.util.concurrent.*;

/**
 * Created by songrongbin on 2017/3/3.
 */
public class TestFutureTask {
    public static void main(String[] args) {
        ExecutorService exec= Executors.newCachedThreadPool();

        FutureTask<String> task=new FutureTask<String>(new Callable<String>(){//FutrueTask的构造参数是一个Callable接口
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return Thread.currentThread().getName();//这里可以是一个异步操作
            }});

        try {
            exec.execute(task);//FutureTask实际上也是一个线程
            String result=task.get();//取得异步计算的结果，如果没有返回，就会一直阻塞等待
            System.out.printf("get:%s%n",result);
            // System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
