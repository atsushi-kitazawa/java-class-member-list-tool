package com.example;

import com.example.ClassMemberList.ListTarget;
import com.example.formatter.Formatter;
import com.example.formatter.SimpleFormatter;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ClassFinder.findClasses(new Visitor<String>() {
            @Override
            public boolean visit(String clazz) {

                Formatter f = new SimpleFormatter();
                new ClassMemberList(f).listMethod(clazz, new ListTarget[]{ListTarget.PUBLIC});

                return true;
            }
        });
    }
}
