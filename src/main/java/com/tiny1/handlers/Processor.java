package com.tiny1.handlers;

import com.tiny1.model.Conf;
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
            String requestString = HttpUtils.getRequest(socket);
            request = new Request(requestString, output);
            // Request object has no nulls in it from now on.

            Handler cth = new ContentTypeHandler(null);
            Handler rh = new ResourceHandler(cth);
            Handler mvh = new MethodValidatorHandler(rh);
            Handler rvh = new RequestValidatorHandler(mvh);
            rvh.handle(request, response);

            HttpUtils.sendResponse(request, response);

        } catch (Exception e) {
            if (Conf.debug)
                e.printStackTrace();
            else
                System.out.println(e.getMessage());
            HttpUtils.sendError(request);
            /*TODO
            Improve error handling as not all exceptions are status 500.
            And request object might be null, maybe find a different place for sendError method.
             */
        }
    }
}
