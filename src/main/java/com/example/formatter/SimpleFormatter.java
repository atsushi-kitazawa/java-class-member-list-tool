package com.example.formatter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SimpleFormatter implements Formatter {

    StringBuffer sb = new StringBuffer();

    @Override
    public void formatClass(Class<?> c) {
        sb.append("class : " + c.getName());
        sb.append(System.getProperty("line.separator"));
    }

    @Override
    public void formatMethod(Method m) {
        sb.append("    Method : " + m.getName());
        sb.append(System.getProperty("line.separator"));
        sb.append("    Annotation : " + arrayToString(m.getDeclaredAnnotations()));
    }

    @Override
    public void formatVariable() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void print() {
        System.out.println(sb.toString());
    }

    private String arrayToString(Annotation[] arrays) {
        List<String> list = new ArrayList<>();
        for (Annotation a : arrays) {
            String name = a.annotationType().getName();
            // System.out.println("debug: " + name);
            list.add(name);
        }
        return list.toString();
    }
}
