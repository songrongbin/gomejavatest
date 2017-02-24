package com.bins.designpattern.abstractfactory.chapterone;

public class SendMailFactory implements Provider {
      
    @Override  
    public Sender produce(){  

        return new MailSender();
    }  
}