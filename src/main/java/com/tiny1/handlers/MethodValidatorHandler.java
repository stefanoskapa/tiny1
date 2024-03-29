package com.tiny1.handlers;

import com.tiny1.model.Request;
import com.tiny1.model.HttpResponses;
import com.tiny1.model.Response;

/**
 * Handler for validating the HTTP method
 * Allowed methods: GET, HEAD
 */
public class MethodValidatorHandler extends Handler {

    public MethodValidatorHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(Request request, Response response) {

        if ("GET".equals(request.getMethod()) || "HEAD".equals(request.getMethod()) )
            return true;
        response.setResponse(HttpResponses.METHOD_NOT_ALLOWED);
        return false;
    }
}
