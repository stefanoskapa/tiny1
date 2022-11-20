package com.tiny1.handlers;

import com.tiny1.model.Handler;
import com.tiny1.model.Request;
import com.tiny1.model.HttpResponses;
import com.tiny1.model.Response;

import java.util.StringTokenizer;

public class MethodValidatorHandler extends Handler {

    public MethodValidatorHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(Request requestObject, Response responseObject) {
        if (requestObject == null || responseObject == null)
            return false;
        if (requestObject.getMethod().equals("GET") || requestObject.getMethod().equals("HEAD")) {
            return true;
        }
        responseObject.setResponse(HttpResponses.METHOD_NOT_ALLOWED);
        return false;
    }
}
