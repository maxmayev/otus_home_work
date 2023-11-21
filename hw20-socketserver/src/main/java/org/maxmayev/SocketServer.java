package org.maxmayev;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketServer implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SocketServer.class);
    String pathRes;
    int port;

    public SocketServer(String pathRes, int port) {
        this.pathRes = pathRes;
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept()) {

            try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {
                String request = input.readLine();
                log.info("request :{}", request);

                if (request.startsWith("GET")) {
                    File file = new File(pathRes + "index.html");
                    if (file.exists()) {
                        output.println("HTTP/1.1 200 OK");
                        output.println("Content-Type: text/html");
                        output.println();
                        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                            String line;
                            while ((line = fileReader.readLine()) != null) {
                                output.println(line);
                            }
                        }
                    } else {
                        output.println("HTTP/1.1 404 Not Found");
                        output.println();
                    }
                } else {
                    output.println("HTTP/1.1 500 Internal Server Error");
                    output.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
