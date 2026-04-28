package com.fintech.practice.core.thread;

public class Day2_SynchronizedFix
{
    static int counter = 0;

    // 1. Способ: синхронизированный метод
    public static synchronized void increment() {
        counter++;  // теперь атомарно!
    }

    public static void main(String[] args) throws InterruptedException
    {
        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                increment();  // защищённый вызов
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Ожидаем: 20_000");
        System.out.println("Реально: " + counter);  // ✅ всегда 20000!
    }
}
