package com.tiny1.server;

import com.tiny1.handlers.*;
import com.tiny1.model.Request;
import com.tiny1.model.Response;
import com.tiny1.util.Console;
import com.tiny1.util.HttpUtils;
import com.tiny1.util.IOUtils;

import java.io.IOException;
import java.net.Socket;


public class Processor {
    public static void handleRequest(Socket socket) {
        Request request = null;
        Response response = new Response();

        try (socket) {

            String requestString = IOUtils.parseRequest(socket);
            request = new Request(requestString, socket);

            Handler cth = new ContentTypeHandler(null);
            Handler rh = new ResourceHandler(cth);
            Handler mvh = new MethodValidatorHandler(rh);
            Handler rvh = new RequestValidatorHandler(mvh);
            rvh.handle(request, response);

            HttpUtils.sendResponse(request, response);

        } catch (Exception e) {
            Console.logErr(e);
            if (request != null) {
                try {
                    HttpUtils.sendError(request);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
