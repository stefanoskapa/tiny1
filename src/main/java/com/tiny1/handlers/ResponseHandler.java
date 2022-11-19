package com.tiny1.handlers;

import com.tiny1.model.Handler;
import com.tiny1.model.Request;
import com.tiny1.util.HttpResponseUtils;

public class ResponseHandler extends Handler {
    public ResponseHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(String request, Request requestObject) throws Exception {

        if (requestObject.getMethod().equals("GET"))
            HttpResponseUtils.sendSuccessResponse(requestObject);
        if (requestObject.getMethod().equals("HEAD"))
            HttpResponseUtils.sendHeadResponse(requestObject);
        return true;
    }
}
