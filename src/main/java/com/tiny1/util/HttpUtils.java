package com.tiny1.util;

import com.tiny1.configuration.Conf;
import com.tiny1.model.Request;
import com.tiny1.model.HttpResponses;
import com.tiny1.model.Response;

import java.io.IOException;
import java.io.PrintWriter;

public class HttpUtils {


    //TODO needs to be rewritten
    public static void sendResponse(Request request, Response response) throws IOException {

//        System.out.println(request);
//        System.out.println(response);
        if (request.getRequestString().isEmpty())
            return;
        PrintWriter pw = new PrintWriter(request.getSocket().getOutputStream());
        pw.print(response.getResponse() + HttpResponses.CRLF);
        if ("GET".equals(request.getMethod()) && response.getResponse().equals(HttpResponses.OK)) {
            pw.print("Content-Type: " + request.getContentType() + HttpResponses.CRLF);
            pw.println();
            pw.flush();
            IOUtils.copy(request.getSocket().getInputStream(), request.getSocket().getOutputStream());
        }
        if ("GET".equals(request.getMethod()) && response.getResponse().equals(HttpResponses.MOVED_PERMANENTLY)) {
            pw.print("Location: " + Conf.getRedirects().get(request.getUri()) + HttpResponses.CRLF);
            pw.println();
            pw.flush();
        }

        pw.close();
        Console.log(request, response.getResponse());
    }

    public static void sendError(Request request) throws IOException {
        PrintWriter pw = new PrintWriter(request.getSocket().getOutputStream());
        pw.print(HttpResponses.INTERNAL_SERVER_ERROR + HttpResponses.CRLF);
        pw.flush();
        pw.close();
        Console.log(request, HttpResponses.INTERNAL_SERVER_ERROR);
    }
}
