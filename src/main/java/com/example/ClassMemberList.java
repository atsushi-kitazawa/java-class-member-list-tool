package com.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
            System.out.println("class : " + c.getSimpleName());

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
        sb.append("    Name : " + m.getName());
        sb.append(System.getProperty("line.separator"));
        sb.append("    Annotation : " + m.getAnnotation(Deprecated.class));

        return sb.toString();
    }
}
