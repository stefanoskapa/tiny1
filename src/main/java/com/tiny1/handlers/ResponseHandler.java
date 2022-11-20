package com.tiny1.handlers;

import com.tiny1.model.Handler;
import com.tiny1.model.Request;
import com.tiny1.model.Response;
import com.tiny1.util.HttpUtils;

public class ResponseHandler extends Handler {
    public ResponseHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(String request, Request requestObject) throws Exception {
        HttpUtils.sendResponse(requestObject, Response.OK);
        return true;
    }
}
