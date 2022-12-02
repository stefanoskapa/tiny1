package com.tiny1.handlers;

import com.tiny1.model.Request;
import com.tiny1.model.Response;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class ResourceHandlerTest {
    ResourceHandler rh = new ResourceHandler(null);
    boolean actual;

    @Test
    public void handle_nullParams_false() {
        actual = rh.handle(null, null);
        assertFalse(actual);
    }
    @Test
    public void handle_nullRequest_false() {
        actual = rh.handle(null, new Response());
        assertFalse(actual);
    }

    @Test
    public void handle_nullResponse_false() {
        actual = rh.handle(new Request(null,null), null);
        assertFalse(actual);
    }

    @Test
    public void handle_nullRequestStringOnRequest_false() {
        OutputStream output = new ByteArrayOutputStream();
        actual = rh.handle(new Request(null,output),new Response());
        assertFalse(actual);
    }

    @Test
    public void handle_nullOutputStreamOnRequest_false() {
        actual = rh.handle(new Request("",null),new Response());
        assertFalse(actual);
    }

}