package com.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ClassMemberList {

    enum ListTarget {
        PUBLIC,
        PRIVATE,
        PROTECTED,
        ANY
    }

    public static void listMethod(String className, ListTarget[] mode) {
        try {
            Class<?> c = Class.forName(className);
            System.out.println("class : " + c.getName());

            for (Method m : c.getDeclaredMethods()) {
                // System.out.println("debug2: " + c.getSimpleName() + ":" + m.getName());
                if(Modifier.isPublic(m.getModifiers())) {
                    String s = formatForPrintMethod(m);
                    System.out.println(s);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void listVariable(String className, ListTarget[] mode) {

    }

    private static String formatForPrintMethod(Method m) {
        StringBuffer sb = new StringBuffer();
        sb.append("    Method : " + m.getName());
        sb.append(System.getProperty("line.separator"));
        sb.append("    Annotation : " + arrayToString(m.getDeclaredAnnotations()));

        return sb.toString();
    }

    private static String arrayToString(Annotation[] arrays) {
        List<String> list = new ArrayList<>();
        for(Annotation a : arrays) {
            String name = a.annotationType().getName();
            // System.out.println("debug: " + name);
            list.add(name);
        }
        return list.toString();
    }
}
