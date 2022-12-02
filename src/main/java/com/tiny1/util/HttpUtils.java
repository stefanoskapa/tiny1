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

        if (numOfBytes == Conf.headerSize) {
            throw new RuntimeException("Header size exceeded");
        }

        if (numOfBytes != -1) {
            String request = new String(requestBytes, 0, numOfBytes);
            requestString.append(request);
        }
        return requestString.toString();
    }


    //TODO needs to be rewritten
    public static void sendResponse(Request requestObject, Response responseObject) throws IOException {

        PrintWriter pw = new PrintWriter(requestObject.getOutput());
        pw.print(responseObject.getResponse() + HttpResponses.CRLF);
        if (requestObject.getMethod().equals("GET") && responseObject.getResponse().equals(HttpResponses.OK)) {
            pw.print("Content-Type: " + requestObject.getContentType() + HttpResponses.CRLF);
            pw.println();
            pw.flush();
            IOUtils.copy(requestObject.getInput(), requestObject.getOutput());
        }
        if (requestObject.getMethod().equals("GET") && responseObject.getResponse().equals(HttpResponses.MOVED_PERMANENTLY)) {
            pw.print("Location: " + Conf.redirects.get(requestObject.getUri()) + HttpResponses.CRLF);
            pw.println();
            pw.flush();
        }
        pw.close();
        requestObject.getOutput().close();

        Console.log(requestObject, responseObject.getResponse());
    }
}
