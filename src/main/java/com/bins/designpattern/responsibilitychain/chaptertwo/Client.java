package com.bins.designpattern.responsibilitychain.chaptertwo;

/**
 * Created by songrongbin on 2016/5/5.
 */
public class Client {
    public static void main(String[] args) {

        ProjectHandler projectHandler =new ProjectHandler();
        DeptHandler deptHandler =new DeptHandler();
        GeneralHandler generalHandler =new GeneralHandler();
        projectHandler.setNextHandler(deptHandler);
        deptHandler.setNextHandler(generalHandler);
        projectHandler.doHandler("lwx", 450);
        projectHandler.doHandler("lwx", 600);
        projectHandler.doHandler("zy", 600);
        projectHandler.doHandler("zy", 1500);
        projectHandler.doHandler("lwxzy", 1500);
    }
}
