package com.tiny1.handlers;

import com.tiny1.model.Handler;
import com.tiny1.model.Request;
import com.tiny1.model.Response;
import com.tiny1.util.HttpUtils;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class RequestValidatorHandler extends Handler {

    public RequestValidatorHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(String request, Request requestObject) throws IOException {
        Pattern pat = Pattern.compile("^[A-Z]{3,7} \\S+ HTTP/\\d.\\d\r\n");
        if (!pat.matcher(request).find()) {
            HttpUtils.sendResponse(requestObject, Response.BAD_REQUEST);
            return false;
        }
        StringTokenizer tokens = new StringTokenizer(request);
        requestObject.setMethod(tokens.nextToken());
        requestObject.setUri(tokens.nextToken());
        requestObject.setHttpTag(tokens.nextToken());

        return true;
    }

}
