package com.tiny1.handlers;

import com.tiny1.exception.NotImplementedException;
import com.tiny1.model.Handler;
import com.tiny1.model.Request;
import com.tiny1.model.Response;
import com.tiny1.util.HttpResponseUtils;

import java.io.IOException;

public class MethodValidatorHandler extends Handler {

    public MethodValidatorHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(String request, Request requestObject) throws IOException {
        if (request.startsWith("GET")) {
            requestObject.setMethod("GET");
            return true;
        } else if (request.startsWith("HEAD")) {
            requestObject.setMethod("HEAD");
            return true;
        }
        HttpResponseUtils.sendResponse(requestObject, Response.METHOD_NOT_ALLOWED);
        return false;


    }
}
