package com.fintech.practice.core.thread;

import org.openjdk.jol.info.ClassLayout;

public class JOLExample
{
    public static void main(String[] args)
    {
        Object obj = new Object();

        // Печатаем раскладку объекта
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
