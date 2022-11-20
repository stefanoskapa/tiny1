package com.tiny1.util;

import com.tiny1.exception.BadRequestException;
import com.tiny1.model.Conf;
import com.tiny1.model.Request;
import com.tiny1.model.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpUtils {

    public static String getRequest(Socket socket) throws Exception {

        StringBuilder requestString = new StringBuilder();
        byte[] requestBytes = new byte[Conf.headerSize];

        InputStream inputStream = socket.getInputStream();
        int numOfBytes = inputStream.read(requestBytes);

        if (numOfBytes == Conf.headerSize) {
            throw new BadRequestException();
        }

        if (numOfBytes != -1) {
            String request = new String(requestBytes, 0, numOfBytes);
            requestString.append(request);
        }
        return requestString.toString();
    }


    public static void sendResponse(Request requestObject, String response) throws IOException {

        PrintWriter pw = new PrintWriter(requestObject.getOutput());
        pw.print(response + Response.CRLF);
        if (requestObject.getMethod().equals("GET") && response.equals(Response.OK)) {
            pw.print("Content-Type: " + requestObject.getContentType() + Response.CRLF);
            pw.println();
            pw.flush();
            IOUtils.copy(requestObject.getInput(), requestObject.getOutput());
        }
        pw.close();
        requestObject.getOutput().close();

        Console.log(requestObject, response);
    }
}
