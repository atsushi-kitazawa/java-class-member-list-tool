package com.example.target;

public class TestTarget {
    private void privateMethod() {
    }

    protected void protectedMethod() {
    }

    @Deprecated
    @TestAnnotation
    @SuppressWarnings("aaa")
    public void publicMethod() {
    }
}
