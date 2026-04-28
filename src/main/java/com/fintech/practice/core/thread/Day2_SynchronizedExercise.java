package com.fintech.practice.core.thread;

public class Day2_SynchronizedExercise
{
    private static int counter = 0;
    private static final Object lock = new Object();  // наш замок

    public static void main(String[] args) throws InterruptedException
    {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                // TODO: оберни counter++ в synchronized блок
                // Подсказка: synchronized(lock) { counter++; }
//                counter++;
                synchronized(lock) { counter++; }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.nanoTime();

        System.out.println("Результат: " + counter);
        System.out.println("Ожидалось: 20000");
        System.out.println("Время: " + (end - start) / 1_000_000 + " мс");
    }
}
