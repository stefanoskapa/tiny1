package com.tiny1.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IOUtilsTest {

    @Test
    public void parseRequest_NullParameter_NullPointerException() {
        assertThrows(NullPointerException.class,()-> IOUtils.parseRequest(null));
    }

}