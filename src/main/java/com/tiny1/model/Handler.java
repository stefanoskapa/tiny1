package com.tiny1.model;

public abstract class Handler {

    private Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    public boolean handle(String request,Request requestObject) throws Exception {
        if (handleImpl(request,requestObject) && next !=null) {
            next.handle(request, requestObject);
            return true;
        }
        return false;
    }

    public abstract boolean handleImpl(String request, Request requestObject) throws Exception;


}
