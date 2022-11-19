package com.tiny1.util;

import com.tiny1.model.Request;
import com.tiny1.model.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        Console.log(requestObject,Response.OK);
    }

    public static void sendHeadResponse(Request requestObject) throws IOException {
        PrintWriter pw = new PrintWriter(requestObject.getOutput());
        pw.print(Response.OK);
        pw.print("Content-Type: " + requestObject.getContentType() + Response.CRLF);
        pw.println();
        pw.flush();
        requestObject.getOutput().close();
        Console.log(requestObject,Response.OK);
    }

    public static void sendNotFound(Request requestObject) throws IOException {
        PrintWriter pw = new PrintWriter(requestObject.getOutput());
        pw.print(Response.NOT_FOUND);
        pw.close();
        requestObject.getOutput().close();
        Console.log(requestObject,Response.NOT_FOUND);
    }

    public static void sendInternalError(Request requestObject) throws IOException {
        PrintWriter pw = new PrintWriter(requestObject.getOutput());
        pw.println(Response.INTERNAL_SERVER_ERROR);
        pw.close();
        requestObject.getOutput().close();
        Console.log(requestObject,Response.INTERNAL_SERVER_ERROR);
    }

    public static void sendMethodNotAllowed(Request requestObject) throws IOException {
        PrintWriter pw = new PrintWriter(requestObject.getOutput());
        pw.print(Response.METHOD_NOT_ALLOWED);
        pw.close();
        requestObject.getOutput().close();
        Console.log(requestObject,Response.METHOD_NOT_ALLOWED);
    }
    public static void sendBadRequest(Request requestObject) throws IOException {
        PrintWriter pw = new PrintWriter(requestObject.getOutput());
        pw.print(Response.BAD_REQUEST);
        pw.close();
        requestObject.getOutput().close();
        Console.log(requestObject,Response.BAD_REQUEST);
    }
}
