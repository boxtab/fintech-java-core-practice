package com.fintech.practice.core.thread;

import org.openjdk.jol.info.ClassLayout;

public class JOLExample
{
    public static void main(String[] args)
    {
//        Object obj = new Object();

        A a = new A();

        // Печатаем раскладку объекта
//        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}

class A {
    byte a;
    int b;
    boolean c;
}

