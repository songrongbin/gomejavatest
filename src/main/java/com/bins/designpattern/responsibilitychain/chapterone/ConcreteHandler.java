package com.bins.designpattern.responsibilitychain.chapterone;

class ConcreteHandler extends Handler {

    @Override
    public void doHandler() {

        if (getNextHandler() != null) {

            System.out.println("还有责任链");
            getNextHandler().doHandler();
        } else {

            System.out.println("我自己处理" + toString());
        }

    }
}