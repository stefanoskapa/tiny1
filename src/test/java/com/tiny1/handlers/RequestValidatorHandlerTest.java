package com.tiny1.handlers;


import com.tiny1.model.Request;
import com.tiny1.model.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestValidatorHandlerTest {

    @Test
    public void testHandleImpl() {
        RequestValidatorHandler rvh = new RequestValidatorHandler(null);
        boolean actual;

        // request == null, response == null
        actual = rvh.handle(null, null);
        assertFalse(actual);

        // response == null
        Request request = new Request();
        actual = rvh.handle(request, null);
        assertFalse(actual);

        // request == null
        Response response = new Response();
        actual = rvh.handle(null, response);
        assertFalse(actual);

        // Request.requestString == null
        actual = rvh.handle(request, response);
        assertFalse(actual);

        // Request.requestString == ""
        request.setRequestString("");
        actual = rvh.handle(null, response);
        assertFalse(actual);

        // Request.requestString == ""
        request.setRequestString("");
        actual = rvh.handle(null, response);
        assertFalse(actual);


    }

}