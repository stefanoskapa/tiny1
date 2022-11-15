package com.tiny1.util;

import com.tiny1.model.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class HttpResponseUtils {

    public static void sendSuccessResponse(InputStream input, OutputStream output,String contentType) throws IOException {
        PrintWriter pw = new PrintWriter(output);
        pw.print(Response.OK);
        pw.print("Content-Type: " + contentType + Response.CRLF);
        pw.println();
        pw.flush();
        IOUtils.copy(input, output);
        output.close();
        Console.showResponse(Response.OK + "Content-Type: " + contentType);
    }

    public static void sendHeadResponse(OutputStream output,String contentType) throws IOException {
        PrintWriter pw = new PrintWriter(output);
        pw.print(Response.OK);
        pw.print("Content-Type: " + contentType + Response.CRLF);
        pw.println();
        pw.flush();
        output.close();
        Console.showResponse(Response.OK + "Content-Type: " + contentType);
    }

    public static void sendNotFound(OutputStream output) throws IOException {
        PrintWriter pw = new PrintWriter(output);
        pw.print(Response.NOT_FOUND);
        pw.close();
        output.close();
        Console.showResponse(Response.NOT_FOUND);
    }

    public static void sendInternalError(OutputStream output) throws IOException {
        PrintWriter pw = new PrintWriter(output);
        pw.println(Response.INTERNAL_SERVER_ERROR);
        pw.close();
        output.close();
        Console.showResponse(Response.INTERNAL_SERVER_ERROR);
    }

    public static void sendMethodNotAllowed(OutputStream output) throws IOException {
        PrintWriter pw = new PrintWriter(output);
        pw.print(Response.METHOD_NOT_ALLOWED);
        pw.close();
        output.close();
        Console.showResponse(Response.METHOD_NOT_ALLOWED);
    }
    public static void sendBadRequest(OutputStream output) throws IOException {
        PrintWriter pw = new PrintWriter(output);
        pw.print(Response.BAD_REQUEST);
        pw.close();
        output.close();
        Console.showResponse(Response.BAD_REQUEST);
    }
}
