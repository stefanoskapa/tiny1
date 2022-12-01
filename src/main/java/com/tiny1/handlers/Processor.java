package com.tiny1.handlers;

import com.tiny1.model.HttpResponses;
import com.tiny1.model.Request;
import com.tiny1.model.Response;
import com.tiny1.util.HttpUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Processor {
    public static void handleRequest(Socket socket) {
        Request request = null;
        Response response = new Response();
        OutputStream output;
        try (socket) {
            output = socket.getOutputStream();
            String rawRequest = HttpUtils.getRequest(socket);
            if (rawRequest.isEmpty()) {
                socket.close();
                return;
            }
            request = new Request(rawRequest,output);

            new RequestValidatorHandler(
                    new MethodValidatorHandler(
                            new ResourceHandler(
                                    new ContentTypeHandler(null)))
            ).handle(request, response);

            HttpUtils.sendResponse(request, response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            try {
                response.setResponse(HttpResponses.INTERNAL_SERVER_ERROR);
                HttpUtils.sendResponse(request, response);
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }
}
