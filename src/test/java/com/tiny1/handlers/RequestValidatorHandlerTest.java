package com.tiny1.handlers;


import com.tiny1.model.Request;
import com.tiny1.model.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestValidatorHandlerTest {

    RequestValidatorHandler rvh;
    boolean actual;

    @BeforeEach
    public void init() {
        rvh = new RequestValidatorHandler(null);
    }

    @Test
    public void testHandle() {

        // request == null, response == null
        actual = rvh.handle(null, null);
        assertFalse(actual);




    }

}