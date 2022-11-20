package com.tiny1.handlers;

import java.io.*;
import java.net.Socket;

import com.tiny1.model.Request;
import com.tiny1.model.HttpResponses;
import com.tiny1.model.Response;
import com.tiny1.util.HttpUtils;


public class RequestHandler {

    public void handleRequest(Socket socket) {
        Request requestObject = new Request();
        Response responseObject = new Response();
        OutputStream output;
        try (socket) {
            output = socket.getOutputStream();
            String rawRequest = HttpUtils.getRequest(socket);
            if (rawRequest.isEmpty()) {
                socket.close();
                return;
            }
            requestObject.setOutput(output);
            requestObject.setRequestString(rawRequest);

            new RequestValidatorHandler(
                    new MethodValidatorHandler(
                            new ResourceHandler(
                                    new ContentTypeHandler(null)))
            ).handle(requestObject, responseObject);

            HttpUtils.sendResponse(requestObject, responseObject);

        } catch (Exception e) {
            e.getMessage();
            try {
                responseObject.setResponse(HttpResponses.INTERNAL_SERVER_ERROR);
                HttpUtils.sendResponse(requestObject, responseObject);
            } catch (IOException e1) {
                e1.getMessage();
            }
        }
    }

}
