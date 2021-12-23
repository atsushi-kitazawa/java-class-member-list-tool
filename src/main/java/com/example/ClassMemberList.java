package com.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import com.example.formatter.Formatter;

public class ClassMemberList {

    enum ListTarget {

        PUBLIC,
        PRIVATE,
        PROTECTED,
        ANY // no implement
    }

    private Formatter formatter;

    public ClassMemberList(Formatter f) {
        formatter = f;
    }

    public void listMethod(String className, ListTarget[] mode) {
        try {
            Class<?> c = Class.forName(className);
            formatter.formatClass(c);

            for (Method m : c.getDeclaredMethods()) {
                // System.out.println("debug2: " + c.getSimpleName() + ":" + m.getName() + ":" +
                // m.getModifiers());
                boolean isTargetForPublic = Modifier.isPublic(m.getModifiers())
                        && Arrays.asList(mode).contains(ListTarget.PUBLIC);
                boolean isTargetForPrivate = Modifier.isPrivate(m.getModifiers())
                        && Arrays.asList(mode).contains(ListTarget.PRIVATE);
                boolean isTargetForProtected = Modifier.isProtected(m.getModifiers())
                        && Arrays.asList(mode).contains(ListTarget.PROTECTED);
                if (isTargetForPublic || isTargetForPrivate || isTargetForProtected) {
                    formatter.formatMethod(m);
                }
            }

            formatter.print();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void listVariable(String className, ListTarget[] mode) {

    }
}
