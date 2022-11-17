package com.tiny1.util;

import com.tiny1.exception.BadRequestException;
import com.tiny1.model.Configuration;

import java.io.InputStream;
import java.net.Socket;

public class HttpUtils {

    public static String getRequest(Socket socket) throws Exception {

        StringBuilder requestString = new StringBuilder();
        byte [] requestBytes = new byte[Configuration.MAX_HTTP_HEADER_SIZE];

        InputStream inputStream = socket.getInputStream();
        int numOfBytes = inputStream.read(requestBytes);

        if (numOfBytes == Configuration.MAX_HTTP_HEADER_SIZE) {
            System.out.println("Head too large");
            throw new BadRequestException();
        }

        if (numOfBytes != -1) {
            String request = new String(requestBytes, 0, numOfBytes);
            requestString.append(request);
        }
        return requestString.toString();
    }


}
