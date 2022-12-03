package com.tiny1.util;

import com.tiny1.model.Conf;
import com.tiny1.model.Request;
import com.tiny1.model.HttpResponses;
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

        if (numOfBytes == Conf.headerSize) // can numOfBytes ever be greater than headerSize?
            throw new RuntimeException("Header size exceeded");

        if (numOfBytes != -1) {
            String request = new String(requestBytes, 0, numOfBytes);
            requestString.append(request);
        }
        return requestString.toString();
    }


    //TODO needs to be rewritten
    public static void sendResponse(Request request, Response response) throws IOException {
        IOUtils.checkNulls(request);
        if (request.getRequestString().isEmpty())
            return;
        PrintWriter pw = new PrintWriter(request.getOutput());
        pw.print(response.getResponse() + HttpResponses.CRLF);
        if (request.getMethod().equals("GET") && response.getResponse().equals(HttpResponses.OK)) {
            pw.print("Content-Type: " + request.getContentType() + HttpResponses.CRLF);
            pw.println();
            IOUtils.copy(request.getInput(), request.getOutput());
        }
        if (request.getMethod().equals("GET") && response.getResponse().equals(HttpResponses.MOVED_PERMANENTLY)) {
            pw.print("Location: " + Conf.redirects.get(request.getUri()) + HttpResponses.CRLF);
            pw.println();
            pw.flush();
        }

        pw.close();
        Console.log(request, response.getResponse());
    }

    public static void sendError(Request request) {
        if (request == null || request.getOutput() == null || request.getRequestString() == null)
            return;
        PrintWriter pw = new PrintWriter(request.getOutput());
        pw.print(HttpResponses.INTERNAL_SERVER_ERROR + HttpResponses.CRLF);
        pw.flush();
        pw.close();
        Console.log(request, HttpResponses.INTERNAL_SERVER_ERROR);
    }
}
