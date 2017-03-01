package com.bins.multithread.continuityoutput;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by songrongbin on 2017/3/1.
 */
public class MutiThreadContinuityOutputDemo {

    ReentrantLock lock = new ReentrantLock();

    private volatile int a = 15;

    class Thread1 extends Thread {
        int a = 0;
        public Thread1(int a) {
            this.a = a;
        }
        @Override
        public void run() {
            lock.lock();
            if(a % 5 == 0 && a >= 5) {
                for(int i = a; i > a - 5; i++ ) {
                    System.out.println("Thread Name:" + Thread.currentThread().getName() + ",a:" + a);
                    a--;
                }
            }
            lock.unlock();
        }
    }



    public static void main(String args[]) {
        MutiThreadContinuityOutputDemo mutiThreadContinuityOutputDemo = new MutiThreadContinuityOutputDemo();
        mutiThreadContinuityOutputDemo.cacl();


    }

    private void cacl() {
        Thread1 thread1 = new Thread1(a);
        Thread1 thread2 = new Thread1(a);
        Thread1 thread3 = new Thread1(a);
        thread1.start();
        thread2.start();
        thread3.start();
    }

}


