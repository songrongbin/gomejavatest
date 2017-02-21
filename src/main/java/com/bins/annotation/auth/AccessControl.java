package com.bins.annotation.auth;

/**
 * Created by songrongbin on 2016/4/13.
 */
public class AccessControl {
    private static String user = "user";

    public static void SetCurrentRole(String userRole) {
        user = userRole;
    }
    public static String getCurrentRole() {
        return user;
    }
}
