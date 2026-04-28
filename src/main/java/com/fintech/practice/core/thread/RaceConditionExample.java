package com.fintech.practice.core.thread;

public class RaceConditionExample {

    private int count = 0;

    public void increment() {
        count++;        // Это НЕ атомарная операция!
    }

    public static void main(String[] args) throws InterruptedException {
        RaceConditionExample example = new RaceConditionExample();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });

        t1.start();
        t2.start();

//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // хорошая практика
//        }

        System.out.println("Финальное значение count = " + example.count);
        // Ожидаем 2000, но почти всегда будет меньше!
    }
}
