package com.bins.annotation.auth;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by songrongbin on 2016/4/13.
 */
public class AccessProxy<T> implements InvocationHandler {
    T accessObj;
    public T bind(T accessObj) {
        this.accessObj = accessObj;
        return (T) Proxy.newProxyInstance(accessObj.getClass().getClassLoader(), accessObj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequiredRoles annotation = method.getAnnotation(RequiredRoles.class);
        if (annotation != null) {
            String[] roles = annotation.value();
            String role = AccessControl.getCurrentRole();
            if (!Arrays.asList(roles).contains(role)) {
                System.out.println("The user is not allowed to invoke this method.");
                return null;
            }
        }
        return method.invoke(accessObj, args);
    }
}
