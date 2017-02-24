package com.bins.designpattern.builder.chapterone;

public class SmsSender implements Sender {
  
    @Override  
    public void Send() {  

        System.out.println("this is sms sender!");
    }  
}