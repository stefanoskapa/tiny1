package com.tiny1.util;

import com.tiny1.model.Request;
import com.tiny1.model.Response;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpResponseUtils {

    public static void sendSuccessResponse(Request requestObject) throws IOException {
        PrintWriter pw = new PrintWriter(requestObject.getOutput());
        pw.print(Response.OK);
        pw.print("Content-Type: " + requestObject.getContentType() + Response.CRLF);
        pw.println();
        pw.flush();
        IOUtils.copy(requestObject.getInput(), requestObject.getOutput());
        requestObject.getOutput().close();
        Console.log(requestObject, Response.OK);
    }

    public static void sendResponse(Request requestObject, String response) throws IOException {
        PrintWriter pw = new PrintWriter(requestObject.getOutput());
        pw.print(response);
        pw.close();
        requestObject.getOutput().close();
        Console.log(requestObject, response);
    }
}
