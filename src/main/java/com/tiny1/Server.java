package com.tiny1;

import com.tiny1.handlers.ContentTypeHandler;
import com.tiny1.handlers.MethodValidatorHandler;
import com.tiny1.handlers.RequestValidatorHandler;
import com.tiny1.handlers.ResourceHandler;
import com.tiny1.model.*;
import com.tiny1.util.ArgParser;
import com.tiny1.util.Console;
import com.tiny1.util.HttpUtils;

import java.io.IOException;
import java.io.OutputStream;
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
            e.printStackTrace();
        }

    }

    public static void handleRequest(Socket socket) {
        Request requestObject = null;
        Response responseObject = new Response();
        OutputStream output;
        try (socket) {
            output = socket.getOutputStream();
            String rawRequest = HttpUtils.getRequest(socket);
            if (rawRequest.isEmpty()) {
                socket.close();
                return;
            }
            requestObject = new Request();
            requestObject.setOutput(output);
            requestObject.setRequestString(rawRequest);

            new RequestValidatorHandler(
                    new MethodValidatorHandler(
                            new ResourceHandler(
                                    new ContentTypeHandler(null)))
            ).handle(requestObject, responseObject);

            HttpUtils.sendResponse(requestObject, responseObject);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            try {
                responseObject.setResponse(HttpResponses.INTERNAL_SERVER_ERROR);
                HttpUtils.sendResponse(requestObject, responseObject);
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }
}