package com.tiny1.handlers;

import com.tiny1.exception.NotImplementedException;
import com.tiny1.model.Handler;
import com.tiny1.model.Request;

public class MethodValidatorHandler extends Handler {

    public MethodValidatorHandler(Handler next) {
        super(next);
    }

    @Override
    public void handleImpl(String request, Request requestObject) throws NotImplementedException {
        if (request.startsWith("GET"))
            requestObject.setMethod("GET");
        else if (request.startsWith("HEAD"))
            requestObject.setMethod("HEAD");
        else
            throw new NotImplementedException();

    }
}
