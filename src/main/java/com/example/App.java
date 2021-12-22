package com.example;

import com.example.ClassMemberList.ListTarget;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ClassFinder.findClasses(new Visitor<String>() {
            @Override
            public boolean visit(String clazz) {

                ClassMemberList.listMethod(clazz, new ListTarget[]{ListTarget.PUBLIC});

                return true;
            }
        });
    }
}
