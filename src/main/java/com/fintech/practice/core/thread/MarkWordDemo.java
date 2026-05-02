package com.fintech.practice.core.thread;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class MarkWordDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== JVM Information ===");
        System.out.println(VM.current().details());

        // 1️⃣ Обычный объект без синхронизации
        System.out.println("\n=== 1. Object WITHOUT synchronization ===");
        Object lonely = new Object();
        System.out.println(ClassLayout.parseInstance(lonely).toPrintable());

        // 2️⃣ Вызываем hashCode() — появляется хэш в Mark Word
        System.out.println("\n=== 2. After calling hashCode() ===");
        int hash = lonely.hashCode();
        System.out.println("Hash code: " + Integer.toHexString(hash));
        System.out.println(ClassLayout.parseInstance(lonely).toPrintable());

        // 3️⃣ Захватываем синхронизацию — появляется ID потока
        System.out.println("\n=== 3. Inside synchronized block ===");
        synchronized (lonely) {
            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println(ClassLayout.parseInstance(lonely).toPrintable());
        }

        // 4️⃣ После выхода из synchronized — Mark Word возвращается?
        System.out.println("\n=== 4. After synchronized lock released ===");
        System.out.println(ClassLayout.parseInstance(lonely).toPrintable());

        // 5️⃣ Бонус: конкуренция (два потока)
        System.out.println("\n=== 5. Two threads competing ===");
        Object contested = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (contested) {
                System.out.println("T1 inside lock");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        }, "Thread-A");

        Thread t2 = new Thread(() -> {
            synchronized (contested) {
                System.out.println("T2 inside lock");
            }
        }, "Thread-B");

        t1.start();
        Thread.sleep(50); // даём T1 захватить объект
        System.out.println("State when T1 holds lock, T2 waiting:");
        System.out.println(ClassLayout.parseInstance(contested).toPrintable());

        t2.start();
        t1.join();
        t2.join();

        System.out.println("\nAfter both threads finished:");
        System.out.println(ClassLayout.parseInstance(contested).toPrintable());
    }
}
