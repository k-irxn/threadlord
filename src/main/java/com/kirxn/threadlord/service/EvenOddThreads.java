package com.kirxn.threadlord.service;

class NumberPrinter {
    private int number = 1;
    private final int limit;

    public NumberPrinter(int limit) {
        this.limit = limit;
    }

    public synchronized void printOdd() {
        while (number <= limit) {
            while (number % 2 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (number <= limit) {
                System.out.println("Odd: " + number);
                number++;
                notify();
            }
        }
    }

    public synchronized void printEven() {
        while (number <= limit) {
            while (number % 2 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (number <= limit) {
                System.out.println("Even: " + number);
                number++;
                notify();
            }
        }
    }
}

public class EvenOddThreads {
    public static void main(String[] args) {
        int limit = 10; // Define the range
        NumberPrinter printer = new NumberPrinter(limit);

        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);

        oddThread.start();
        evenThread.start();
    }
}

