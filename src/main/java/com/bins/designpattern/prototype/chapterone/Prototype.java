package com.bins.designpattern.prototype.chapterone;

public class Prototype implements Cloneable {
  
    public Object clone() throws CloneNotSupportedException {  
        Prototype proto = (Prototype) super.clone();  
        return proto;  
    }  
}