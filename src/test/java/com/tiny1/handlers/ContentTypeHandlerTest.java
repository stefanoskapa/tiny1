package com.tiny1.handlers;

import com.tiny1.model.Request;
import com.tiny1.model.Response;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class ContentTypeHandlerTest {
    final ContentTypeHandler cth = new ContentTypeHandler(null);

    @Test
    public void handle_nullParams_false() {
        assertThrows(NullPointerException.class, () -> cth.handle(null, null));
    }
    @Test
    public void handle_nullRequest_false() {
        assertThrows(NullPointerException.class, () ->  cth.handle(null, new Response()));
    }

    @Test
    public void handle_nullResponse_false() {
        assertThrows(NullPointerException.class, () -> cth.handle(new Request(null,null), null));
    }

    @Test
    public void handle_nullRequestStringOnRequest_false() {
        OutputStream output = new ByteArrayOutputStream();
        assertThrows(NullPointerException.class, () -> cth.handle(new Request(null,output),new Response()));
    }

    @Test
    public void handle_nullOutputStreamOnRequest_false() {
        assertThrows(NullPointerException.class, () -> cth.handle(new Request("",null),new Response()));
    }


}