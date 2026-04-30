package com.fintech.practice.core.thread;

public class ThreadAttributes
{
    public static void main(String[] args)
    {
        int x = 5;
        foo(x);
    }

    static void foo(int a)
    {
        if (a > 0) {
            bar(a);
        }
        System.out.println("foo end");
    }

    static void bar(int b)
    {
        System.out.println("bar: " + b);
    }
}
