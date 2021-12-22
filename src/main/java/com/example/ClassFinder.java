package com.example;

import java.io.File;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassFinder {

    private static final String TARGET_JAE = ".jar";
    private static final String TARGET_CLASS = ".class";
    private static final String TARGET_PACKAGE = "com.example";

    public static void findClasses(Visitor<String> visitor) {
        String classpath = System.getProperty("java.class.path");
        String[] paths = classpath.split(System.getProperty("path.separator"));

        String javaHome = System.getProperty("java.home");
        File file = new File(javaHome + File.separator + "lib");
        if (file.exists()) {
            findClasses(file, file, true, visitor);
        }

        for (String path : paths) {
            file = new File(path);
            if (file.exists()) {
                findClasses(file, file, false, visitor);
            }
        }
    }

    private static boolean findClasses(File root, File file, boolean includeJars, Visitor<String> visitor) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                if (!findClasses(root, child, includeJars, visitor)) {
                    return false;
                }
            }
        } else {
            if (file.getName().toLowerCase().endsWith(".jar") && includeJars) {
                // list class in jar file.
                // System.out.println("debug1: " + file.getName());
                JarFile jar = null;
                try {
                    jar = new JarFile(file);
                } catch (Exception ex) {

                }
                if (jar != null) {
                    Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        int extIndex = name.lastIndexOf(".class");
                        if (extIndex > 0) {
                            name = name.substring(0, extIndex).replace("/", ".");
                            // System.out.println("debug4: " + name);
                            if(!name.startsWith(TARGET_PACKAGE)) {
                                continue;
                            }
                            if (!visitor.visit(name)) {
                                return false;
                            }
                        }
                    }
                }
            } else if (file.getName().toLowerCase().endsWith(".class")) {
                // list class not in jar file.
                // System.out.println("debug2: " + file.getName());
                String className = createClassName(root, file);
                if(!className.startsWith(TARGET_PACKAGE)) {
                    return false;
                }

                // System.out.println("debug3: " + className);
                if (!visitor.visit(className)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static String createClassName(File root, File file) {
        StringBuffer sb = new StringBuffer();
        String fileName = file.getName();
        sb.append(fileName.substring(0, fileName.lastIndexOf(".class")));
        file = file.getParentFile();
        while (file != null && !file.equals(root)) {
            sb.insert(0, '.').insert(0, file.getName());
            file = file.getParentFile();
        }
        return sb.toString();
    }
}
