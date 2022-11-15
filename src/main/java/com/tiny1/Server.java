package com.tiny1;

import com.tiny1.model.RequestRunnable;
import com.tiny1.util.Console;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        int port = 8000;

        Console.showLogo();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ExecutorService executor = Executors.newFixedThreadPool(5);
            System.out.println("Server started on port " + port);
            System.out.println("Waiting for incoming connections...\n");

            while(true) {
                Socket socket = serverSocket.accept();
                executor.submit(new RequestRunnable(socket));
            }
        } catch (Exception e) {
            System.out.println("FATAL ERROR: " + e.getMessage());
        }

    }

}