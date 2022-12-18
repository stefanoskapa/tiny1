package com.tiny1.handlers;

import com.tiny1.model.Request;
import com.tiny1.model.Response;
import com.tiny1.util.Console;
import com.tiny1.util.HttpUtils;
import com.tiny1.util.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Processor {
    public static void handleRequest(Socket socket) {
        Request request = null;
        Response response = new Response();
        OutputStream output;

        try (socket) {
            output = socket.getOutputStream(); //throws IOException
            String requestString = IOUtils.parseRequest(socket);
            request = new Request(requestString, output);
            // Request object has no nulls in it from now on.

            Handler cth = new ContentTypeHandler(null);
            Handler rh = new ResourceHandler(cth);
            Handler mvh = new MethodValidatorHandler(rh);
            Handler rvh = new RequestValidatorHandler(mvh);
            rvh.handle(request, response);

            HttpUtils.sendResponse(request, response);

        }

        catch (IOException e) {
            Console.logErr(e);
        } catch (Exception e) { //unexpected runtime exceptions
            Console.logErr(e);
            HttpUtils.sendError(request);
        }
    }
}
