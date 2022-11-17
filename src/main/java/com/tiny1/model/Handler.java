package com.tiny1.model;

public abstract class Handler {

    private Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    public void handle(String request,Request requestObject) throws Exception {
        handleImpl(request,requestObject);
        if (next == null)
            return;
        next.handle(request,requestObject);
    }

    public abstract void handleImpl(String request, Request requestObject) throws Exception;


}
