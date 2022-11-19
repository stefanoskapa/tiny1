package com.tiny1.handlers;

import com.tiny1.exception.NotFoundException;
import com.tiny1.model.Handler;
import com.tiny1.model.Request;
import com.tiny1.util.HttpResponseUtils;
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

        if (input == null) {
            HttpResponseUtils.sendNotFound(requestObject.getOutput());
            return false;

        }


        requestObject.setInput(input);
        requestObject.setUri(uri);
        return true;



    }
}
