package com.bins.lock;

import java.util.concurrent.locks.Lock;
/** 
 * @说明 儿子类，只消费 
 */  
public class Consumer implements Runnable {  
    BankCard bc = null;  
    Lock lock = null;  
    Consumer(BankCard bc, Lock lock) {  
        this.bc = bc;
    this.lock = lock;
}
    public void run() {  
        try {  
            while(true){  
                //lock.lock();
                System.out.print(Thread.currentThread().getName() + ":" + "儿子要消费，现在余额：" + bc.getBalance() + "\t");
                bc.setBalance(bc.getBalance() - 2000);  
                System.out.println(Thread.currentThread().getName() + ":" + "儿子消费2000元，现在余额：" + bc.getBalance());
                //lock.unlock();
                Thread.sleep(1 * 10);
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }         
    }  
}  