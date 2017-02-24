package com.bins.designpattern.factorymethod.chapterthree;

public class MailSender implements Sender {
    @Override  
    public void Send() {  

        System.out.println("this is mailsender!");
    }  
}