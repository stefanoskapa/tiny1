package com.tiny1.handlers;

import java.io.*;
import java.net.Socket;

import com.tiny1.model.Request;
import com.tiny1.model.Response;
import com.tiny1.util.HttpUtils;


public class RequestHandler {

    public void handleRequest(Socket socket) {
        Request requestObject = new Request();
        OutputStream output;
        try (socket) {
            output = socket.getOutputStream();
            String request = HttpUtils.getRequest(socket);
            if (request.isEmpty()) {
                socket.close();
                return;
            }
            requestObject.setOutput(output);

            new RequestValidatorHandler(
                    new MethodValidatorHandler(
                            new ResourceHandler(
                                    new ContentTypeHandler(
                                            new ResponseHandler(null))))
            ).handle(request, requestObject);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                HttpUtils.sendResponse(requestObject, Response.INTERNAL_SERVER_ERROR);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
