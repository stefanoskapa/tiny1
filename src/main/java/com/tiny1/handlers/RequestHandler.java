package com.tiny1.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

import com.tiny1.util.HttpResponseUtils;
import com.tiny1.util.HttpUtils;
import com.tiny1.util.IOUtils;


public class RequestHandler {

    public void handleRequest(Socket socket) {
        OutputStream output = null;

        try (socket) {
            output = socket.getOutputStream();
            String request = HttpUtils.getRequest(socket);

            String uri = HttpUtils.getRequestUri(request);
            String contentType = decideContentType(uri);
            String method = HttpUtils.getMethod(request);

            //System.out.println("[" + method + "]" + uri + " -> Response Content-Type: " + contentType.trim());
            System.out.println(request);
            InputStream in = IOUtils.getResource(uri);

            if (method == null || !method.equals("GET")) {
                HttpResponseUtils.sendMethodNotAllowed(output);
                return;
            }
            if (in == null) {
                HttpResponseUtils.sendNotFound(output);
                return;
            }

            HttpResponseUtils.sendSuccessResponse(in,output,contentType);

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
        String fileExtension="";
        String[] temp = string.split("\\.");
        if (temp.length > 1)
            fileExtension= temp[temp.length-1];

        return fileExtension;
    }
    private String decideContentType(String uri) {
        String contentType;
        try {
            Properties prop = new Properties();
            prop.load(RequestHandler.class.getClassLoader().getResourceAsStream("mime.properties"));
            String extension = getExtension(uri);
            contentType = prop.getProperty(extension);

            if (contentType ==null)
                contentType = "Content-type: text/html\r\n"; //default content type
            return contentType + "\r\n";
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            contentType = "Content-type: text/html\r\n"; //default content type
        }
        return contentType;
    }
}
