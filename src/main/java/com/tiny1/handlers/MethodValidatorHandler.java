package com.tiny1.handlers;

import com.tiny1.model.Request;
import com.tiny1.model.HttpResponses;
import com.tiny1.model.Response;

public class MethodValidatorHandler extends Handler {

    public MethodValidatorHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(Request request, Response response) {
        if (request.getMethod().equals("GET") || request.getMethod().equals("HEAD"))
            return true;
        response.setResponse(HttpResponses.METHOD_NOT_ALLOWED);
        return false;
    }
}
