package main.java;

import java.util.concurrent.locks.Lock;

public class Job implements Runnable {
    private static final int LIMIT = 10;
    private String name;
    private Lock lock;

    public Job(String name, Lock lock) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 1; i < LIMIT; i++) {
            printValue(i);
        }

        for (int i = LIMIT; i > 0; i--) {
            printValue(i);
        }
    }

    private void printValue(int i) {
        lock.lock();
        try {
            System.out.println(name + " : " + i);
        } finally {
            lock.unlock();
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
