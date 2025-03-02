package com.kirxn.threadlord.service;

import java.util.concurrent.*;

public class VirtualThreadBenchmark {
    public static void main(String[] args) throws InterruptedException {
        int numTasks = 10_000;
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        long start = System.currentTimeMillis();

        for (int i = 0; i < numTasks; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(200); // Simulating an I/O operation
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();
        System.out.println("Execution Time (Virtual Threads): " + (end - start) + " ms");
    }
}

