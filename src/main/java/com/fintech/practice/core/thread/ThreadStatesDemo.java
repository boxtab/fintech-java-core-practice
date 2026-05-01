package com.fintech.practice.core.thread;

public class ThreadStatesDemo {

    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {

        // 1. NEW
        Thread t1 = new Thread(() -> {
            System.out.println("t1 начал работу");

            // BLOCKED — будем ждать lock
            synchronized (LOCK) {
                System.out.println("t1 захватил LOCK");
                sleep(3000);               // держим lock 3 секунды
            }

            // WAITING — ждём уведомления
            synchronized (LOCK) {
                try {
                    System.out.println("t1 перешёл в WAITING");
                    LOCK.wait();               // бесконечное ожидание
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Thread-1");

        // 2. TIMED_WAITING через sleep
        Thread t2 = new Thread(() -> {
            System.out.println("t2 начал работу");
            sleep(1500);                   // TIMED_WAITING
            System.out.println("t2 завершил sleep");
        }, "Thread-2");

        // Выводим состояние до запуска
        printState("До start()", t1, t2);

        t1.start();
        t2.start();

        printState("Сразу после start()", t1, t2);

        // Даём потокам немного поработать
        Thread.sleep(500);
        printState("Через 500мс", t1, t2);

        Thread.sleep(2000);
        printState("Через ~2.5 секунды", t1, t2);

        // Разбудим t1 из WAITING
        synchronized (LOCK) {
            LOCK.notify();
            System.out.println("Главный поток сделал notify()");
        }

        t1.join();
        t2.join();

        printState("После завершения", t1, t2);
    }

    private static void printState(String moment, Thread... threads) {
        System.out.println("\n--- " + moment + " ---");
        for (Thread t : threads) {
            System.out.printf("%s : %s%n", t.getName(), t.getState());
        }
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
