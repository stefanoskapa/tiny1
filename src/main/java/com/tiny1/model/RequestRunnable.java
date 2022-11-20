package com.tiny1.model;


import com.tiny1.Server;
import com.tiny1.handlers.RequestHandler;
import java.net.Socket;

public class RequestRunnable implements Runnable {

    Socket socket;

    public RequestRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Server.handleRequest(socket);
    }

}