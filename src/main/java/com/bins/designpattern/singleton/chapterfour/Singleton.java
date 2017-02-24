package com.bins.designpattern.singleton.chapterfour;

public class Singleton {
  
    private static Singleton instance = null;
  
    private Singleton() {
    }  
  
    private static synchronized void syncInit() {  
        if (instance == null) {  
            instance = new Singleton();
        }  
    }  
  
    public static Singleton getInstance() {
        if (instance == null) {  
            syncInit();  
        }  
        return instance;  
    }  
} 