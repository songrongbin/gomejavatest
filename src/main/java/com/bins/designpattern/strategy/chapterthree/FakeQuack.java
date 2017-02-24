package com.bins.designpattern.strategy.chapterthree;

public class FakeQuack implements QuackBehavior {
    public void quack() {  
        System.out.println("Qwak");  
    }  
}