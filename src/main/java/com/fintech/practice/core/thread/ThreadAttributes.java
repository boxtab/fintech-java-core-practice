package com.fintech.practice.core.thread;

public class ThreadAttributes {

    private static final ThreadLocal<String> USER = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            USER.set("Alice");
            System.out.println("[" + Thread.currentThread().getName() + "] установил USER = Alice");

            sleep(100);

            System.out.println("[" + Thread.currentThread().getName() + "] читает: " + USER.get());
        }, "Thread-Alice");

        Thread t2 = new Thread(() -> {
            USER.set("Bob");
            System.out.println("[" + Thread.currentThread().getName() + "] установил USER = Bob");

            sleep(100);

            System.out.println("[" + Thread.currentThread().getName() + "] читает: " + USER.get());
        }, "Thread-Bob");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("[" + Thread.currentThread().getName() + "] в main: " + USER.get());
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}