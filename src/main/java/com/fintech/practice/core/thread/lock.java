package com.fintech.practice.core.thread;

import java.util.Set;

public class lock
{
    public static void main(String[] args)
    {
        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1 waiting...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {}
                System.out.println("Thread 1 resumed");
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 notifying...");
                lock.notify();
            }
        }).start();
    }
}
