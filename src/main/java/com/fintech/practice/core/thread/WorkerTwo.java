package com.fintech.practice.core.thread;

import java.util.Random;

public class WorkerTwo
{
    public static void main(String[] args)
    {
        System.out.println("Main thread started: " + Thread.currentThread().getName());

        Runnable task = () -> {
            Random random = new Random();

            while (true) {
                int number = 10 + random.nextInt(91); // 10–100
                System.out.println(
                        "Thread: " + Thread.currentThread().getName() +
                                " | Number: " + number
                );

                try {
                    int delay = 3000 + random.nextInt(3000); // 3–6 сек
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + Thread.currentThread().getName());
                    break;
                }
            }
        };

        Thread worker1 = new Thread(task, "Worker-1");
        Thread worker2 = new Thread(task, "Worker-2");

        worker1.start();
        worker2.start();
    }
}
