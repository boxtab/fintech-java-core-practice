package com.fintech.practice.core.thread;

public class VisualSynchronized
{
    private static int counter = 0;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException
    {
        Runnable task = () -> {
            String name = Thread.currentThread().getName();

            for (int i = 1; i <= 30; i++) {
                System.out.println(name + " ▶ ПЫТАЮСЬ захватить монитор (попытка " + i + ")");

                synchronized (LOCK) {
                    System.out.println(name + " ✅ ЗАХВАТИЛ монитор! counter=" + counter);
                    counter++;

                    // Имитация работы внутри критической секции
                    try {
                        Thread.sleep(2_000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(name + " ❌ ОСВОБОЖДАЮ монитор. counter=" + counter);
                }

                // Не критическая работа
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t1 = new Thread(task, "🔵 Поток Аврора");
        Thread t2 = new Thread(task, "🟠 Поток Борисполь");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("\nФИНАЛЬНЫЙ СЧЁТЧИК: " + counter);
    }
}
