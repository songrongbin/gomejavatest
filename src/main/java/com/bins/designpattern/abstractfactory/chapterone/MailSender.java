package com.bins.designpattern.abstractfactory.chapterone;

public class MailSender implements Sender {
    @Override  
    public void Send() {  

        System.out.println("this is mailsender!");
    }  
} 