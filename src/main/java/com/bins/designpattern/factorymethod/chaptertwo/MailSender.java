package com.bins.designpattern.factorymethod.chaptertwo;

public class MailSender implements Sender {
    @Override  
    public void Send() {  

        System.out.println("this is mailsender!");
    }  
}