package org.example;

public class Main {
    public static void main(String[] args) {
        MyLogQueue myLogQueue = new MyLogQueue();
        for (int i = 0; i < 10; i++) {
            myLogQueue.add("Adding");
            myLogQueue.delete("Deleting");
        }
    }
}