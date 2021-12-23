package com.example.formatter;

import java.lang.reflect.Method;

public interface Formatter {
    void formatClass(Class<?> c);

    void formatMethod(Method m);

    void formatVariable();

    void print();
}
