package com.tiny1.handlers;


import com.tiny1.model.Request;
import com.tiny1.model.Response;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class RequestValidatorHandlerTest {

    final RequestValidatorHandler rvh = new RequestValidatorHandler(null);

    @Test
    public void handle_nullParams_false() {
        assertThrows(NullPointerException.class, () -> rvh.handle(null, null));
    }
    @Test
    public void handle_nullRequest_false() {
        assertThrows(NullPointerException.class, () ->  rvh.handle(null, new Response()));
    }

    @Test
    public void handle_nullResponse_false() {
        assertThrows(NullPointerException.class, () -> rvh.handle(new Request(null,null), null));
    }

    @Test
    public void handle_nullRequestStringOnRequest_false() {
        OutputStream output = new ByteArrayOutputStream();
        assertThrows(NullPointerException.class, () -> rvh.handle(new Request(null,output),new Response()));
    }

    @Test
    public void handle_nullOutputStreamOnRequest_false() {
        assertThrows(NullPointerException.class, () -> rvh.handle(new Request("",null),new Response()));
    }


}