package com.bins.guava.optional;

import org.junit.Test;

public class NullTest {
    @Test
    public void testNull(){
        int age = 1;
        System.out.println("user age:"+age);
        
        long money = 1l;
        money=10L;
        System.out.println("user money"+money);
        
        String name = "abc";
        System.out.println("user name:"+name);
    }
}