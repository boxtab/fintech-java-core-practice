package com.fintech.practice.core.thread;

import java.util.Set;

public class ThreadList
{
    public static void main(String[] args)
    {
        Set<Thread> threads = Thread.getAllStackTraces().keySet();

        System.out.printf("%-25s %-15s %-10s %-10s%n",
                "Name", "State", "Priority", "Daemon");

        for (Thread t : threads) {
            System.out.printf("%-25s %-15s %-10d %-10s%n",
                    t.getName(),
                    t.getState(),
                    t.getPriority(),
                    t.isDaemon());
        }
    }
}
