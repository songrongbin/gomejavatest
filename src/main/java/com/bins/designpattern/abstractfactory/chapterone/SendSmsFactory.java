package com.bins.designpattern.abstractfactory.chapterone;

public class SendSmsFactory implements Provider{
  
    @Override  
    public Sender produce() {  

        return new SmsSender();
    }  
}