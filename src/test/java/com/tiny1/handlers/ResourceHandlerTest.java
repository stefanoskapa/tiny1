package com.tiny1.handlers;

import com.tiny1.model.Request;
import com.tiny1.model.Response;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class ResourceHandlerTest {
    final ResourceHandler rh = new ResourceHandler(null);
    @Test
    public void handle_nullParams_false() {
        assertThrows(NullPointerException.class, () -> rh.handle(null, null));
    }
    @Test
    public void handle_nullRequest_false() {
        assertThrows(NullPointerException.class, () ->  rh.handle(null, new Response()));
    }

    @Test
    public void handle_nullResponse_false() {
        assertThrows(NullPointerException.class, () -> rh.handle(new Request(null,null), null));
    }

    @Test
    public void handle_nullRequestStringOnRequest_false() {
        OutputStream output = new ByteArrayOutputStream();
        assertThrows(NullPointerException.class, () -> rh.handle(new Request(null,output),new Response()));
    }

    @Test
    public void handle_nullOutputStreamOnRequest_false() {
        assertThrows(NullPointerException.class, () -> rh.handle(new Request("",null),new Response()));
    }
}