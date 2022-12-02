package com.tiny1.handlers;

import com.tiny1.model.Request;
import com.tiny1.model.HttpResponses;
import com.tiny1.model.Response;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class RequestValidatorHandler extends Handler {
    private static final Pattern PATTERN = Pattern.compile("^[A-Z]{3,7} \\S{1,2048} HTTP/\\d.\\d\r\n");
    public RequestValidatorHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(Request request, Response response) {
        if (!PATTERN.matcher(request.getRequestString()).find()) {
            response.setResponse(HttpResponses.BAD_REQUEST);
            return false;
        }
        StringTokenizer tokens = new StringTokenizer(request.getRequestString());
        request.setMethod(tokens.nextToken());
        request.setUri(tokens.nextToken());
        request.setHttpTag(tokens.nextToken());

        return true;
    }

}
