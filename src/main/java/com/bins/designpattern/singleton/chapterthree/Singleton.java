package com.bins.designpattern.singleton.chapterthree;

public class Singleton {

    private static Singleton singleton = null;
  
    /* 私有构造方法，防止被实例化 */  
    private Singleton() {
    }

    private static class SingletonFactory {
        private static Singleton singleton = new Singleton();
    }

    public Singleton getSingletonInstance() {
        return SingletonFactory.singleton;
    }


}