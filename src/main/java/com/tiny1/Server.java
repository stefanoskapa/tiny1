package com.tiny1;

import com.tiny1.model.Conf;
import com.tiny1.model.RequestRunnable;
import com.tiny1.util.ArgParser;
import com.tiny1.util.Console;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {

        ArgParser parser = new ArgParser(args);
        parser.parse();

        Console.showLogo();
        Console.showSettings();

        try (ServerSocket serverSocket = new ServerSocket(Conf.port)) {
            ExecutorService executor = Executors.newFixedThreadPool(Conf.poolSize);
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