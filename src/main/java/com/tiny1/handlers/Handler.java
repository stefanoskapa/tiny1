package com.tiny1.handlers;

import com.tiny1.model.Request;
import com.tiny1.model.Response;

public abstract class Handler {

    private final Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    public boolean handle(Request request, Response response) {
        if (request == null
                || response == null
                || request.getRequestString() == null
                || request.getOutput() == null)
            return false;

        boolean isSuccess = handleImpl(request, response);

        if (next != null && isSuccess)
            next.handle(request, response);

        return isSuccess;
    }

    public abstract boolean handleImpl(Request requestObject, Response responseObject);

}
