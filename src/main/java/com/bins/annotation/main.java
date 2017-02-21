package com.bins.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class main {
    public static void main(String[] args) {
        try {
            Class clazz = TestClass.class;
            Object obj = clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
            for (Field field : clazz.getDeclaredFields()) {
                id myId = field.getAnnotation(id.class);
                if (myId != null) {
                    System.out.println(field.getName() + "是ID字段");
                } else {
                    System.out.println(field.getName() + "非ID字段");
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}