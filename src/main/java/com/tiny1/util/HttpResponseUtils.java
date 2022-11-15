package com.tiny1.util;

import com.tiny1.model.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class HttpResponseUtils {

    public static void sendSuccessResponse(InputStream in, OutputStream out,String contentType) throws IOException {
        PrintWriter pw = new PrintWriter(out);
        pw.print(Response.OK);
        pw.print("Content-Type: " + contentType + Response.CRLF);
        pw.println();
        pw.flush();
        IOUtils.copy(in, out);
        out.close();
        Console.showResponse(Response.OK + "Content-Type: " + contentType);
    }

    public static void sendNotFound(OutputStream out) throws IOException {
        PrintWriter pw = new PrintWriter(out);
        pw.print(Response.NOT_FOUND);
        pw.close();
        out.close();
        Console.showResponse(Response.NOT_FOUND);
    }

    public static void sendInternalError(OutputStream out) throws IOException {
        PrintWriter pw = new PrintWriter(out);
        pw.println(Response.INTERNAL_SERVER_ERROR);
        pw.close();
        out.close();
        Console.showResponse(Response.INTERNAL_SERVER_ERROR);
    }

    public static void sendMethodNotAllowed(OutputStream out) throws IOException {
        PrintWriter pw = new PrintWriter(out);
        pw.print(Response.METHOD_NOT_ALLOWED);
        pw.close();
        out.close();
        Console.showResponse(Response.METHOD_NOT_ALLOWED);
    }
}
