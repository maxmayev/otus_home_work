package org.example;

import java.util.concurrent.LinkedBlockingQueue;

public class MyLogQueue {

    LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public void add(String value) {
        if (queue.add(value)) {
            System.out.println("Added " + value);
            new Thread(() -> {
                new Writer().write("deleted.txt", value);
            }).start();
        }
    }

    public void delete(String value) {
        if (queue.remove(value)) {
            System.out.println("Deleted " + value);
            new Thread(() -> {
                new Writer().write("inserted.txt", value);
            }).start();
        }
    }


}
