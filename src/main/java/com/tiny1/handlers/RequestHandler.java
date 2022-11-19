package com.tiny1.handlers;

import java.io.*;
import java.net.Socket;
import com.tiny1.model.Request;
import com.tiny1.util.Console;
import com.tiny1.util.HttpResponseUtils;
import com.tiny1.util.HttpUtils;


public class RequestHandler {

    public void handleRequest(Socket socket) {
        OutputStream output = null;
        try (socket) {
            output = socket.getOutputStream();
            String request = HttpUtils.getRequest(socket);
            if (request.isEmpty()) {
                socket.close();
                return;
            }

            Console.showRequest(request);

            Request requestObject = new Request();
            requestObject.setOutput(output);

            new RequestValidatorHandler(
                    new MethodValidatorHandler(
                            new ResourceHandler(
                                    new ContentTypeHandler(
                                            new ResponseHandler(null))))
            ).handle(request, requestObject);

        }  catch (Exception e) {
            e.printStackTrace();
            if (output != null) {
                try {
                    HttpResponseUtils.sendInternalError(output);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
