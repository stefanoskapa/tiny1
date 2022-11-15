package com.tiny1.handlers;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

import com.tiny1.exception.BadRequestException;
import com.tiny1.exception.EmptyRequestException;
import com.tiny1.util.Console;
import com.tiny1.util.HttpResponseUtils;
import com.tiny1.util.HttpUtils;
import com.tiny1.util.IOUtils;


public class RequestHandler {

    public void handleRequest(Socket socket) {
        OutputStream output = null;
        try (socket) {
            output = socket.getOutputStream();
            String request = HttpUtils.getRequest(socket);
            if (request.isEmpty())
                throw new EmptyRequestException();
            Console.showRequest(request);

            String uri = HttpUtils.getRequestUri(request);
            String contentType = decideContentType(uri);
            String method = HttpUtils.getMethod(request);

            InputStream in = IOUtils.getResource(uri);

            if (method == null || !method.equals("GET")) {
                HttpResponseUtils.sendMethodNotAllowed(output);
                return;
            }
            if (in == null) {
                HttpResponseUtils.sendNotFound(output);
                return;
            }

            HttpResponseUtils.sendSuccessResponse(in, output, contentType);
        } catch (EmptyRequestException e) {
            //nothing to do here, socket is closed
        }catch (BadRequestException e){

            try {
                HttpResponseUtils.sendBadRequest(output);
                socket.close();
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

    private String getExtension(String string) {
        String fileExtension = "";
        String[] temp = string.split("\\.");
        if (temp.length > 1)
            fileExtension = temp[temp.length - 1];

        return fileExtension;
    }

    private String decideContentType(String uri) {
        String contentType;

        try (InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("mime.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String extension = getExtension(uri);
            contentType = prop.getProperty(extension);
            return contentType;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            contentType = "Content-type: text/html\r\n"; //default content type
        }


        return contentType;
    }

}
