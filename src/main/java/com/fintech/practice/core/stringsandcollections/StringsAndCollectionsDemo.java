package com.fintech.practice.core.stringsandcollections;

/**
 * День 2: Строки + Arrays + базовые коллекции
 * Финтех-практика (ордера, логи, market data)
 */
public class StringsAndCollectionsDemo {

    public static void main(String[] args) {
        System.out.println("=== День 2: Строки + Arrays + Базовые коллекции ===\n");

        task1();
        task2();
        task3();
        task4();
        task5();
    }

    private static void task1() {
        System.out.println("Задание 1: String — immutable");

        String orderId = "ORD-123456";
        String newOrderId = orderId.replace("ORD", "TRADE");

        System.out.println("Исходная строка: " + orderId);
        System.out.println("После replace: " + newOrderId);
        System.out.println("Строки равны по == ? " + (orderId == newOrderId));
        System.out.println("Строки равны по equals? " + orderId.equals(newOrderId));

        System.out.println("\nВывод: Каждый метод String, который \"меняет\" строку, создаёт новый объект.\n");
    }

    private static void task2() {
        System.out.println("Задание 2: String vs StringBuilder (конкатенация в цикле)");

        // Плохой способ — конкатенация String в цикле
        long start1 = System.currentTimeMillis();
        String bad = "";
        for (int i = 0; i < 50_000; i++) {
            bad += "order" + i + ";";
        }
        long end1 = System.currentTimeMillis();

        // Хороший способ
        long start2 = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50_000; i++) {
            sb.append("order").append(i).append(";");
        }
        String good = sb.toString();
        long end2 = System.currentTimeMillis();

        System.out.println("Время String (+=)     : " + (end1 - start1) + " мс");
        System.out.println("Время StringBuilder   : " + (end2 - start2) + " мс");
        System.out.println("Длина строки: " + good.length() + "\n");
    }

    private static void task3() {
        System.out.println("Задание 3: Arrays и полезные методы");

        int[] prices = {45000, 45210, 44890, 45150, 45300};

        System.out.println("Исходный массив: " + java.util.Arrays.toString(prices));
        java.util.Arrays.sort(prices);
        System.out.println("После сортировки: " + java.util.Arrays.toString(prices));
//        System.out.println("Содержит 45210? " + java.util.Arrays.binarySearch(prices, 45210) >= 0);

        System.out.println();
    }

    private static void task4() {
        System.out.println("Задание 4: ArrayList vs LinkedList (первое знакомство)");

        java.util.List<String> arrayList = new java.util.ArrayList<>();
        java.util.List<String> linkedList = new java.util.LinkedList<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            arrayList.add("order-" + i);
        }
        System.out.println("ArrayList добавление в конец: " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            linkedList.add("order-" + i);
        }
        System.out.println("LinkedList добавление в конец: " + (System.currentTimeMillis() - start) + " мс\n");
    }

    private static void task5() {
        System.out.println("Задание 5: HashMap basics");

        java.util.Map<String, Long> orderVolumes = new java.util.HashMap<>();

        orderVolumes.put("BTCUSDT", 1_250_000L);
        orderVolumes.put("ETHUSDT", 8_450_000L);
        orderVolumes.put("SOLUSDT",  450_000L);

        System.out.println("Объём BTCUSDT: " + orderVolumes.get("BTCUSDT"));
        System.out.println("Содержит ключ SOLUSDT? " + orderVolumes.containsKey("SOLUSDT"));
        System.out.println("Количество записей: " + orderVolumes.size());

        System.out.println("\nВывод: HashMap — основная структура для быстрого поиска по ключу в финтехе.\n");
    }
}
