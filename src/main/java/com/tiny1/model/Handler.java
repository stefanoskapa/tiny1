package com.tiny1.model;

public abstract class Handler {

    private Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    public boolean handle(Request requestObject, Response responseObject) {
        if (requestObject == null || responseObject == null)
            return false;
        if (handleImpl(requestObject,responseObject) && next !=null) {
            next.handle(requestObject,responseObject);
            return true;
        }
        return false;
    }

    public abstract boolean handleImpl(Request requestObject, Response responseObject);


}
