package com.tiny1.handlers;

import com.tiny1.model.Handler;
import com.tiny1.model.Request;
import com.tiny1.model.Response;
import com.tiny1.util.HttpUtils;

import java.io.IOException;
import java.util.StringTokenizer;

public class MethodValidatorHandler extends Handler {

    public MethodValidatorHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(String request, Request requestObject) throws IOException {
        if (request.startsWith("GET") || request.startsWith("HEAD")) {
            StringTokenizer tokens = new StringTokenizer(request);
            requestObject.setMethod(tokens.nextToken());
            return true;
        }
        HttpUtils.sendResponse(requestObject, Response.METHOD_NOT_ALLOWED);
        return false;
    }
}
