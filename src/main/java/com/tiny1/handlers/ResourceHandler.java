package com.tiny1.handlers;

import com.tiny1.model.Handler;
import com.tiny1.model.Request;
import com.tiny1.model.Response;
import com.tiny1.util.HttpUtils;
import com.tiny1.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

public class ResourceHandler extends Handler {
    public ResourceHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(String request, Request requestObject) throws IOException {
        StringTokenizer tokens = new StringTokenizer(request);
        tokens.nextToken();
        String uri = tokens.nextToken();

        InputStream input = IOUtils.getResource(uri);
        requestObject.setUri(uri);
        if ((input == null) || uri.contains("..")) { //path traversal attack
            HttpUtils.sendResponse(requestObject, Response.NOT_FOUND);
            return false;
        }

        requestObject.setInput(input);
        return true;
    }


}
