package com.bins.annotation;
public class TestClass {
    public TestClass() {
    }
    @id
    private String testClassId;
    private String name;
    public String getTestClassId() {
        return testClassId;
    }
    public void setTestClassId(String testClassId) {
        this.testClassId = testClassId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}