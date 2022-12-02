package com.tiny1.handlers;

import com.tiny1.model.Conf;
import com.tiny1.model.Request;
import com.tiny1.model.HttpResponses;
import com.tiny1.model.Response;
import com.tiny1.util.IOUtils;

import java.io.InputStream;
import java.util.StringTokenizer;

public class ResourceHandler extends Handler {
    public ResourceHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(Request request, Response response) {
        StringTokenizer tokens = new StringTokenizer(request.getRequestString());
        tokens.nextToken();
        String uri = tokens.nextToken();

        if (Conf.redirects.containsKey(uri)) {
            response.setResponse(HttpResponses.MOVED_PERMANENTLY);
            return false;
        }

        InputStream input = IOUtils.getResource(uri);
        request.setUri(uri);
        if ((input == null) || uri.contains("..")) { //path traversal attack
            response.setResponse(HttpResponses.NOT_FOUND);
            return false;
        }
        response.setResponse(HttpResponses.OK);
        request.setInput(input);
        return true;
    }


}
