package com.tiny1.model;


import com.tiny1.handlers.RequestHandler;
import java.net.Socket;

public class RequestRunnable implements Runnable {

    Socket socket;

    RequestHandler requestHandler = new RequestHandler();

    public RequestRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        requestHandler.handleRequest(socket);
    }

}