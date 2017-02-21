package com.bins.annotation;

/**
 * @author liuqing
 */
public class UserInfoTest {

    @ComAnnotation(name = "编号", order = 1)
    private String id;
    @ComAnnotation(name = "姓名", order = 44)
    private String name;

    private int age;

    private String sex;

    private boolean isUsb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ComAnnotation(order = 2)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ComAnnotation(name = "性别方法", order = 3)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @ComAnnotation(name = "其它方法", order = 3)
    public boolean isUsb() {
        return isUsb;
    }

    public void setUsb(boolean isUsb) {
        this.isUsb = isUsb;
    }

}
