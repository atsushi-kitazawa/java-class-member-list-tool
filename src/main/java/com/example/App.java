package com.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ClassFinder.findClasses(new Visitor<String>() {
            @Override
            public boolean visit(String clazz) {
                // System.out.println(clazz);

                ClassMemberList.listMethod(clazz, null);
                
                return true;
            }
        });
    }
}
