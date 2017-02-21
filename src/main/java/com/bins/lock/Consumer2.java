package com.bins.lock;

import java.util.concurrent.locks.Lock;
/** 
 * @说明 父亲类，只挣钱 
 */  
public class Consumer2 implements Runnable {  
    BankCard bc = null;  
    Lock lock = null;  
    Consumer2(BankCard bc, Lock lock) {  
        this.bc = bc;  
        this.lock = lock;  
    }  
    public void run() {  
        try {  
            while(true){  
                lock.lock();
                System.out.print(Thread.currentThread().getName() + ":" + "父亲要存钱，现在余额：" + bc.getBalance() + "\t");
                bc.setBalance(bc.getBalance() + 500);  
                System.out.println(Thread.currentThread().getName() + ":" + "父亲存入500元，现在余额：" + bc.getBalance());
                lock.unlock();
                Thread.sleep(3 * 10);
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }         
    }  
}