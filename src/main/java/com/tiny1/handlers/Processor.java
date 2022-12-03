package com.tiny1.handlers;

import com.tiny1.model.Request;
import com.tiny1.model.Response;
import com.tiny1.util.HttpUtils;

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
            request = new Request(rawRequest, output);

            Handler cth = new ContentTypeHandler(null);
            Handler rh = new ResourceHandler(cth);
            Handler mvh = new MethodValidatorHandler(rh);
            Handler rvh = new RequestValidatorHandler(mvh);
            rvh.handle(request, response);

            HttpUtils.sendResponse(request, response);

        } catch (Exception e) {
            System.out.println("CRITICAL ERROR: " + e.getMessage());
            HttpUtils.sendError(request);
        }
    }
}
