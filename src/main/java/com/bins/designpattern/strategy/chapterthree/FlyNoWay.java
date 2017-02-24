package com.bins.designpattern.strategy.chapterthree;

public class FlyNoWay implements FlyBehavior {
    public void fly() {  
        System.out.println("I can't fly");  
    }  
}