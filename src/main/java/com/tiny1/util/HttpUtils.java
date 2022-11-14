package com.tiny1.util;

import java.io.InputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class HttpUtils {

    public static String getRequest(Socket socket) throws Exception {

        StringBuilder requestString = new StringBuilder();

        byte [] requestBytes = new byte[40000];

        InputStream inputStream = socket.getInputStream();
        int numOfBytes = inputStream.read(requestBytes);

        String request = null;

        if (numOfBytes != -1) {
            request = new String(requestBytes, 0, numOfBytes);
            requestString.append(request);
        }

        if (request != null && request.contains("multipart/form-data")) {
            numOfBytes = inputStream.read(requestBytes);
            if (numOfBytes != -1) {
                requestString.append( new String(requestBytes, 0, numOfBytes));
            }
        }



        return requestString.toString();
    }

    public static String getRequestUri(String request) {
        StringTokenizer stk = new StringTokenizer(request);
        stk.nextToken(); //skip method
        return stk.hasMoreTokens() ? stk.nextToken() : null;
    }

    public static String getMethod(String request) {
        StringTokenizer stk = new StringTokenizer(request);
        return stk.hasMoreTokens() ? stk.nextToken() : null;
    }
}
