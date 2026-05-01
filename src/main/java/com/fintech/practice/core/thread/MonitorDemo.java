package com.fintech.practice.core.thread;

public class MonitorDemo {

    private static final Object LOCK = new Object();   // Этот объект имеет монитор

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {                  // t1 захватывает монитор объекта LOCK
                System.out.println("Thread-1: Я захватил монитор и сплю 5 секунд...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread-1: Я выхожу из synchronized блока.");
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            System.out.println("Thread-2: Пытаюсь захватить монитор...");
            synchronized (LOCK) {                  // t2 будет ждать здесь
                System.out.println("Thread-2: Наконец-то захватил монитор!");
            }
        }, "Thread-2");

        t1.start();
        Thread.sleep(1000);   // даём t1 время захватить lock
        t2.start();

        // Чтобы успеть посмотреть в дебаггере — ставим точку останова здесь:
        Thread.sleep(2000);
        System.out.println("Main: Программа продолжается...");
    }
}
