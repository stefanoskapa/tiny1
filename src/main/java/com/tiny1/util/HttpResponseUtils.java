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
        pw.print("Content-Type: " + contentType);
        pw.println();
        pw.flush();
        IOUtils.copy(in, out);
        out.close();
    }

    public static void sendNotFound(OutputStream out) throws IOException {
        PrintWriter pw = new PrintWriter(out);
        pw.print(Response.NOT_FOUND);
        pw.close();
        out.close();
    }

    public static void sendInternalError(OutputStream out) throws IOException {
        PrintWriter pw = new PrintWriter(out);
        pw.println(Response.INTERNAL_SERVER_ERROR);
        pw.close();
        out.close();
    }

    public static void sendMethodNotAllowed(OutputStream out) throws IOException {
        PrintWriter pw = new PrintWriter(out);
        pw.print(Response.METHOD_NOT_ALLOWED);
        pw.close();
        out.close();
    }
}
