package com.tiny1;

import com.tiny1.model.RequestRunnable;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        int port = 8000;
        System.out.println();
        System.out.println("   _/      _/                        _/");
        System.out.println("_/_/_/_/      _/_/_/    _/    _/  _/_/");
        System.out.println(" _/      _/  _/    _/  _/    _/    _/");
        System.out.println("_/      _/  _/    _/  _/    _/    _/");
        System.out.println(" _/_/  _/  _/    _/    _/_/_/    _/       v0.1");
        System.out.println("                          _/");
        System.out.println("                     _/_/\n");


        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ExecutorService executor = Executors.newFixedThreadPool(5);
            System.out.println("Server started on port " + port);
            System.out.println("Waiting for incoming connections...\n");

            for (; ; ) {
                Socket socket = serverSocket.accept();
                executor.submit(new RequestRunnable(socket));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}