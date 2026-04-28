package com.fintech.practice.core.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterDemo {

    // ==================== ВАРИАНТ 1: Проблема (Race Condition) ====================
    static class UnsafeCounter {
        private int counter = 0;

        public void increment() {
            counter++;        // НЕ атомарная операция!
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) {}
        }

        public int getValue() {
            return counter;
        }
    }

    // ==================== ВАРИАНТ 2: Исправлено с помощью synchronized ====================
    static class SafeSynchronizedCounter {
        private int counter = 0;

        public synchronized void increment() {   // или synchronized (this)
            counter++;
        }

        public int getValue() {
            return counter;
        }
    }

    // ==================== ВАРИАНТ 3: Исправлено с помощью AtomicInteger ====================
    static class SafeAtomicCounter {
        private final AtomicInteger counter = new AtomicInteger(0);

        public void increment() {
            counter.incrementAndGet();   // атомарная операция
        }

        public int getValue() {
            return counter.get();
        }
    }

    // ==================== Запуск демонстрации ====================
    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== Демонстрация Race Condition ===\n");

        // === 1. Небезопасный вариант ===
        UnsafeCounter unsafe = new UnsafeCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) unsafe.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) unsafe.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.printf("1. Без защиты (int + counter++):     %d (ожидаем 2000)%n", unsafe.getValue());

        // === 2. С synchronized ===
        SafeSynchronizedCounter syncCounter = new SafeSynchronizedCounter();

        t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) syncCounter.increment();
        });

        t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) syncCounter.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.printf("2. С synchronized:                    %d (всегда 2000)%n", syncCounter.getValue());

        // === 3. С AtomicInteger ===
        SafeAtomicCounter atomicCounter = new SafeAtomicCounter();

        t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) atomicCounter.increment();
        });

        t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) atomicCounter.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.printf("3. С AtomicInteger:                   %d (всегда 2000)%n", atomicCounter.getValue());
    }
}
