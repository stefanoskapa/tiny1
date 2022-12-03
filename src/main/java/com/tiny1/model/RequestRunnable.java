package com.tiny1.model;


import com.tiny1.handlers.Processor;

import java.net.Socket;

public class RequestRunnable implements Runnable {

    final Socket socket;

    public RequestRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Processor.handleRequest(socket);
    }

}