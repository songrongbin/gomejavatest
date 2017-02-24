package com.bins.designpattern.factorymethod.chapterthree;

public class FactoryTest {

    public static void main(String[] args) {
        Sender sender = SendFactory.produceMail();
        sender.Send();
    }  
}