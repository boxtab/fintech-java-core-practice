package com.fintech.practice.core.thread;

// Просто запустить 3 потока и увидеть, как они "бегают"
public class Day1_SimpleThreads
{
    public static void main(String[] args)
    {
        for (int i = 1; i <= 3; i++) {
            final int id = i;
            new Thread(() -> {
                for (int j = 1; j <= 3; j++) {
                    System.out.println("Сотрудник " + id + " делает шаг " + j);
                    try {
                        Thread.sleep(100); // имитация работы
                    } catch (InterruptedException e) {}
                }
            }).start();
        }
    }
}
// Запустите 5 раз — порядок будет РАЗНЫЙ!
// Вот многопоточность в действии — порядок не гарантирован
