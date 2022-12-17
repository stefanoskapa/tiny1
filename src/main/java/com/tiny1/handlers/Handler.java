package com.tiny1.handlers;

import com.tiny1.model.Request;
import com.tiny1.model.Response;
import com.tiny1.util.IOUtils;

public abstract class Handler {

    private final Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    /**
     * Calls handleImpl() and runs the next handler in the chain.
     *
     * @param request a Request object
     * @param response a Response object
     * @return if the processing is completed
     */
    public boolean handle(Request request, Response response) throws NullPointerException{
        if (IOUtils.checkNulls(request))
            throw new NullPointerException("Null in Request Object");
        boolean isSuccess = handleImpl(request, response);

        if (next != null && isSuccess)
            next.handle(request, response);

        return isSuccess;
    }

    public abstract boolean handleImpl(Request requestObject, Response responseObject);

}
