package com.bins.annotation.auth;

/**
 * Created by songrongbin on 2016/4/13.
 */
public class Visit implements IVisit {
    @Override
    public void visitRole() {
        System.out.println("I have visit something！");
    }
}
