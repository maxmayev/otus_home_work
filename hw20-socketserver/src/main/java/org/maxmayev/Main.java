package org.maxmayev;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        final int port = 8080;
        File resources = new File("src/main/resources");
        String pathRes = resources.getAbsolutePath() + File.separator;
        SocketServer socketServer = new SocketServer(pathRes, port);

        Thread thread = new Thread(socketServer);

        thread.start();

    }
}