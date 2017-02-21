package com.bins.annotation.auth;

/**
 * Created by songrongbin on 2016/4/13.
 */
public class TestAnnotation {
    public static void main(String[] args) {
        Visit v = new Visit();
        AccessControl.SetCurrentRole("root");
        //AccessControl.SetCurrentRole("user");
        AccessProxy<Visit> handler = new AccessProxy<Visit>();
        IVisit visitProxy = handler.bind(v);
        visitProxy.visitRole();
    }
}
