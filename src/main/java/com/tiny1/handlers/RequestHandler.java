package com.tiny1.handlers;

import java.io.*;
import java.net.Socket;

import com.tiny1.exception.BadRequestException;
import com.tiny1.exception.EmptyRequestException;
import com.tiny1.exception.NotFoundException;
import com.tiny1.exception.NotImplementedException;
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
            if (request.isEmpty())
                throw new EmptyRequestException();
            Console.showRequest(request);

            Request requestObject = new Request();
            requestObject.setOutput(output);

            ResponseHandler resh = new ResponseHandler(null);
            ContentTypeHandler cth = new ContentTypeHandler(resh);
            ResourceHandler rh = new ResourceHandler(cth);
            MethodValidatorHandler mvh = new MethodValidatorHandler(rh);
            RequestValidatorHandler rvh = new RequestValidatorHandler(mvh);
            rvh.handle(request, requestObject);

        } catch (NotFoundException e) {
            try {
                HttpResponseUtils.sendNotFound(output);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (NotImplementedException e) {
            try {
                HttpResponseUtils.sendMethodNotAllowed(output);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } catch (EmptyRequestException e) {
            //nothing to do here, socket is closed
        } catch (BadRequestException e) {
            try {
                HttpResponseUtils.sendBadRequest(output);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
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
