package com.fintech.practice.core.primitives;

import java.math.BigDecimal;

/**
 * День 1: Примитивные типы + обёртки + autoboxing
 * Финтех-практика
 */
public class PrimitiveTypesDemo {

    public static void main(String[] args) {
        System.out.println("=== Java Core: Примитивные типы (Финтех практика) ===\n");

//        task1();
//        task2();
//        task3();
        task4();
//        task5();
    }

    // ================== ЗАДАНИЯ ==================

    private static void task1() {
        System.out.println("Задание 1: Размеры примитивов и их реальное использование");

        byte orderSide = 1;           // 1 = BUY, -1 = SELL
        short quantitySmall = 100;
        int quantity = 1_500_000;     // объём в лотах
        long orderId = 987654321012345L;

        System.out.println("orderSide (byte)  = " + orderSide);
        System.out.println("quantity (int)    = " + quantity);
        System.out.println("orderId (long)    = " + orderId);
        System.out.println("long занимает " + Long.BYTES + " байт, диапазон до ~9e18\n");

        // Вопрос для самопроверки:
        // Почему для orderId почти всегда используют long, а не int?
    }

    private static void task2() {
        System.out.println("Задание 2: long vs int в трейдинге");

        long timestampMs = System.currentTimeMillis();   // время в миллисекундах
        long volume = 1_234_567_890L;                    // большой объём торгов

        int badVolume = 2_147_483_647; // максимум int
         badVolume = badVolume + 1;   // ← раскомментируй и посмотри, что будет (overflow!)

        System.out.println("Текущее время (ms): " + timestampMs);
        System.out.println("Объём торгов (long): " + badVolume);
        System.out.println("Максимальное значение int: " + Integer.MAX_VALUE + "\n");
    }

    private static void task3() {
        System.out.println("Задание 3: double и проблема точности (цены)");

        double price1 = 0.1;
        double price2 = 0.2;
        double sum = price1 + price2;

        System.out.println("0.1 + 0.2 = " + sum);           // ожидаешь 0.3 ?
        System.out.println("0.1 + 0.2 == 0.3 ? " + (sum == 0.3));

        // Почему так происходит и что используют в финтехе вместо double?
    }

    private static void task4() {
        System.out.println("Задание 4: Автоупаковка (autoboxing) и её влияние на производительность");

        // Плохой вариант — использование обёртки Long в цикле
        long start1 = System.currentTimeMillis();

        Long sumWrapper = 0L;                    // ← Long (объект), а не примитив long
        for (int i = 0; i < 10_000_000; i++) {   // 10 миллионов итераций
            sumWrapper += i;                     // Здесь происходит autoboxing + unboxing на каждой итерации!
        }

        long end1 = System.currentTimeMillis();
        System.out.println("Время с Long (обёртка): " + (end1 - start1) + " мс");
        System.out.println("Результат: " + sumWrapper);

        // Хороший вариант — использование примитивного типа long
        long start2 = System.currentTimeMillis();

        long sumPrimitive = 0L;
        for (int i = 0; i < 10_000_000; i++) {
            sumPrimitive += i;                   // Простое сложение примитивов — очень быстро
        }

        long end2 = System.currentTimeMillis();
        System.out.println("Время с long (примитив): " + (end2 - start2) + " мс");
        System.out.println("Результат: " + sumPrimitive);

        System.out.println("\nВывод: autoboxing создаёт новые объекты Long на каждой итерации → большой overhead.\n");
    }

    private static void task5() {
        System.out.println("Задание 5: BigDecimal — правильный способ работать с деньгами");

        // Правильный способ сложить 0.1 + 0.2
        BigDecimal price1 = new BigDecimal("0.1");
        BigDecimal price2 = new BigDecimal("0.2");
        BigDecimal sum = price1.add(price2);

        System.out.println("BigDecimal: 0.1 + 0.2 = " + sum);
        System.out.println("sum.equals(new BigDecimal(\"0.3\")) ? " + sum.equals(new BigDecimal("0.3")));

        // Ещё один пример — цена акции
        BigDecimal stockPrice = new BigDecimal("1234.5678");
        System.out.println("Цена акции: " + stockPrice);

        System.out.println("\nВывод: В финтехе для денег используем либо long (в копейках/тиках), либо BigDecimal\n");
    }
}
