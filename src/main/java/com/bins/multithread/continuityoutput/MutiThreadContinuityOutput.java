package com.bins.multithread.continuityoutput;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by songrongbin on 2017/3/1.
 */
public class MutiThreadContinuityOutput {

    private volatile int a = 15;

    private ReentrantLock lock = new ReentrantLock();

    class Thread1 extends Thread {
        @Override
        public void run() {
            lock.lock();
            for (int i = 1; i < 6; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadName:" + Thread.currentThread().getName() + ",a:" + i);
            }
            lock.unlock();
        }
    }

    class Thread2 extends Thread {
        @Override
        public void run() {
            lock.lock();
            for (int i = 6; i < 11; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadName:" + Thread.currentThread().getName() + ",a:" + i);
            }
            lock.unlock();
        }
    }

    class Thread3 extends Thread {
        @Override
        public void run() {
            lock.lock();
            for (int i = 11; i < 16; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadName:" + Thread.currentThread().getName() + ",a:" + i);
            }
            lock.unlock();
        }
    }

    public static void main(String args[]) {
        MutiThreadContinuityOutput mutiThreadContinuityOutput = new MutiThreadContinuityOutput();
        mutiThreadContinuityOutput.cacl();
    }

    private void cacl() {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        Thread3 thread3 = new Thread3();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread1.start();
            thread2.start();
            thread3.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


