package com.fintech.practice.core.thread;

public class VisibilityDemo
{
    private volatile static boolean running = true;  // Без volatile!

    public static void main(String[] args) throws InterruptedException
    {
        Thread worker = new Thread(() -> {
            long count = 0;
            while (running) {  // БЕСКОНЕЧНЫЙ ЦИКЛ!
                count++;
            }
            System.out.println("Worker finished after " + count);
        });

        worker.start();
        Thread.sleep(1000);  // даём поработать

        running = false;  // пытаемся остановить
        System.out.println("Main set running = false");

        worker.join();  // НИКОГДА НЕ ЗАВЕРШИТСЯ!
        // Поток worker навсегда застрял в цикле,
        // потому что читает running=true из кэша
    }
}
