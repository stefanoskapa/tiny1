package com.tiny1.handlers;

import com.tiny1.model.Handler;
import com.tiny1.model.Request;
import com.tiny1.model.HttpResponses;
import com.tiny1.model.Response;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class RequestValidatorHandler extends Handler {

    public RequestValidatorHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(Request requestObject, Response responseObject) {
        if (requestObject == null || responseObject == null || requestObject.getRequestString() == null)
            return false;
        Pattern pat = Pattern.compile("^[A-Z]{3,7} \\S{1,2048} HTTP/\\d.\\d\r\n");
        if (!pat.matcher(requestObject.getRequestString()).find()) {
            responseObject.setResponse(HttpResponses.BAD_REQUEST);
            return false;
        }
        StringTokenizer tokens = new StringTokenizer(requestObject.getRequestString());
        requestObject.setMethod(tokens.nextToken());
        requestObject.setUri(tokens.nextToken());
        requestObject.setHttpTag(tokens.nextToken());

        return true;
    }

}
