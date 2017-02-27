package com.bins.multithread.masterworkerplus;

public class PlusWorker extends Worker {

    @Override
    public Object handle(Object input) {
        Integer i = (Integer) input;
        return i*i*i;
    }
    
}