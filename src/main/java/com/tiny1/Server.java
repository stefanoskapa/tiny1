package com.tiny1;

import com.tiny1.model.Defaults;
import com.tiny1.model.RequestRunnable;
import com.tiny1.util.Console;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {

        Console.showLogo();

        try (ServerSocket serverSocket = new ServerSocket(Defaults.PORT)) {
            ExecutorService executor = Executors.newFixedThreadPool(Defaults.THREAD_POOL);
            System.out.println("Server started on port " + Defaults.PORT);
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