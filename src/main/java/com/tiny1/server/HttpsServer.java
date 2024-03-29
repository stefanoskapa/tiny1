package com.tiny1.server;

import com.tiny1.configuration.Conf;
import com.tiny1.model.RequestRunnable;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.*;

public class HttpsServer {


    public static void start()  {


        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();


        try (ServerSocket serverSocket = ssf.createServerSocket(Conf.getPort())) {
            ExecutorService executor = Executors.newFixedThreadPool(Conf.getPoolSize());
            System.out.println("Waiting for incoming connections...\n");

            while (true) {
                SSLSocket sslsocket = (SSLSocket) serverSocket.accept();
                executor.submit(new RequestRunnable(sslsocket));
            }
        } catch (Exception e) {
            System.out.println("FATAL ERROR: " + e.getMessage());
            e.printStackTrace();
        }


    }


}
