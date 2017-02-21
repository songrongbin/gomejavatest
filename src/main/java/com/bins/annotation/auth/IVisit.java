package com.bins.annotation.auth;

/**
 * Created by songrongbin on 2016/4/13.
 */
public interface IVisit {
    @RequiredRoles("root")
    public void visitRole();
}
