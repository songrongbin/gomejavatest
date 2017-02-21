package com.bins.designpattern.responsibilitychain.chapterone;

/**
 * Created by songrongbin on 2016/5/5.
 */
public class Client {
    public static void main(String[] args) {
        ConcreteHandler handler1 = new ConcreteHandler();
        ConcreteHandler handler2 = new ConcreteHandler();
        handler1.setNextHandler(handler2);
        handler1.doHandler();
    }

}
