package com.tiny1.model;

public abstract class Handler {

    private Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    public boolean handle(String request,Request requestObject, Response responseObject) throws Exception {
        if (handleImpl(request,requestObject,responseObject) && next !=null) {
            next.handle(request, requestObject,responseObject);
            return true;
        }
        return false;
    }

    public abstract boolean handleImpl(String request, Request requestObject, Response responseObject) throws Exception;


}
