package com.tiny1.handlers;

import com.tiny1.util.HttpResponseUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MethodHandler {
    public static void handleMethod(String method, InputStream input, OutputStream output, String contentType) throws IOException {

        switch (method) {
            case "GET" -> HttpResponseUtils.sendSuccessResponse(input, output, contentType);
            case "HEAD" -> HttpResponseUtils.sendHeadResponse(output, contentType);
            default -> HttpResponseUtils.sendMethodNotAllowed(output);
        }
    }
}
