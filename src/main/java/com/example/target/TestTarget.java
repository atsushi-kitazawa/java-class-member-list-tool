package com.example.target;

public class TestTarget {
    private void privateMethod() {
    }

    protected void protectedMethod() {
    }

    @Deprecated
    @TestAnnotation("abc")
    @SuppressWarnings("aaa")
    public void publicMethod() {
    }
}
