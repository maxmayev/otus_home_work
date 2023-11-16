package org.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        Lock lock = new ReentrantLock();
        Thread worker1 = new Thread(new Job("Thread 1", lock));
        Thread worker2 = new Thread(new Job("Thread 2", lock));


        worker1.start();
        Thread.sleep(100);
        worker2.start();
    }
}