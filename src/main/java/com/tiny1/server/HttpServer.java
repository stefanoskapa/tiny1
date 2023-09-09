package com.tiny1.server;

import com.tiny1.configuration.Conf;
import com.tiny1.model.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    public static void start() {

        try (ServerSocket serverSocket = new ServerSocket(Conf.getPort())) {
            ExecutorService executor = Executors.newFixedThreadPool(Conf.getPoolSize());
            System.out.println("Waiting for incoming connections...\n");

            while (true) {
                Socket socket = serverSocket.accept();
                executor.submit(new RequestRunnable(socket));
            }
        } catch (Exception e) {
            System.out.println("FATAL ERROR: " + e.getMessage());
            e.printStackTrace();
        }

    }

}