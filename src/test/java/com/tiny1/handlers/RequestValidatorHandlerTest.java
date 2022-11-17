package com.tiny1.handlers;

import com.tiny1.exception.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestValidatorHandlerTest {

    @Test
    public void testHandleImpl(){
        RequestValidatorHandler handler = new RequestValidatorHandler(null);
        String string = "GET / HTTP/1.1\r\n";
        assertDoesNotThrow(() -> handler.handleImpl(string,null));

        String string1 = "GET // HTTP/1.0";
        assertThrows(BadRequestException.class,()-> handler.handleImpl(string1,null));

        String string2 = "GET /a.txt HTTP/1.1\r\n";
        assertDoesNotThrow(() -> handler.handleImpl(string2,null));

        String string3 = "GET /a.txt HTTP/1.1\r\nasdfffdfsa   fdsaf";
        assertDoesNotThrow(() -> handler.handleImpl(string3,null));

        String string4="GET /fonts/nixieone-regular-webfont.woff HTTP/1.1\r\n";
        assertDoesNotThrow(() -> handler.handleImpl(string4,null));
    }

}