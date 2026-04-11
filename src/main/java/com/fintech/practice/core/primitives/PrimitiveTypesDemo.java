package com.fintech.practice.core.primitives;

/**
 * День 1: Примитивные типы + обёртки + autoboxing
 * Финтех-практика
 */
public class PrimitiveTypesDemo {

    public static void main(String[] args) {
        System.out.println("=== Java Core: Примитивные типы (Финтех практика) ===\n");

        task1();
        task2();
        task3();
        task4();
        task5();
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
        // badVolume = badVolume + 1;   // ← раскомментируй и посмотри, что будет (overflow!)

        System.out.println("Текущее время (ms): " + timestampMs);
        System.out.println("Объём торгов (long): " + volume);
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
        System.out.println("Задание 4: Автоупаковка (autoboxing) и производительность");

        long start = System.currentTimeMillis();

        Long sum = 0L;                    // ← обратите внимание: Long (обёртка), а не long
        for (int i = 0; i < 1_000_000; i++) {
            sum += i;                     // здесь происходит autoboxing на каждой итерации!
        }

        long end = System.currentTimeMillis();

        System.out.println("Время выполнения с Long: " + (end - start) + " мс");
        System.out.println("sum = " + sum + "\n");

        // Задание: перепиши sum на примитив long и сравни скорость
    }

    private static void task5() {
        System.out.println("Задание 5: BigDecimal — правильный способ работать с деньгами");

        // TODO: Напиши код, который правильно посчитает 0.1 + 0.2
        // и выведет результат точно равный 0.3

        System.out.println("BigDecimal должен показать точный результат 0.3\n");
    }
}
