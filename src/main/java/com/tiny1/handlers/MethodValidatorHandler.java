package com.tiny1.handlers;

import com.tiny1.model.Handler;
import com.tiny1.model.Request;
import com.tiny1.model.HttpResponses;
import com.tiny1.model.Response;
import com.tiny1.util.HttpUtils;

import java.io.IOException;
import java.util.StringTokenizer;

public class MethodValidatorHandler extends Handler {

    public MethodValidatorHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(String request, Request requestObject, Response responseObject) {
        if (request.startsWith("GET") || request.startsWith("HEAD")) {
            StringTokenizer tokens = new StringTokenizer(request);
            requestObject.setMethod(tokens.nextToken());
            return true;
        }
        responseObject.setResponse(HttpResponses.METHOD_NOT_ALLOWED);
        return false;
    }
}
