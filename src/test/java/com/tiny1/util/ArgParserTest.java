package com.tiny1.util;

import com.tiny1.model.Conf;
import com.tiny1.model.Defaults;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgParserTest {

    @Test
    public void testParse() {

        //test defaults
        assertEquals(Defaults.PORT,Conf.port);
        assertEquals(Defaults.MAX_HTTP_HEADER_SIZE,Conf.headerSize);
        assertEquals(Defaults.THREAD_POOL,Conf.poolSize);

        //test port changes
        String[] args = {"-p", "80"};
        ArgParser parser = new ArgParser(args);
        parser.parse();
        assertEquals(80,Conf.port);

        String[] args1 = {"--port", "87"};
        parser = new ArgParser(args1);
        parser.parse();
        assertEquals(87,Conf.port);

        //change pool size
        String[] args2 = {"-ps", "2"};
        parser = new ArgParser(args2);
        parser.parse();
        assertEquals(2,Conf.poolSize);

        String[] args3 = {"--pool-size", "6"};
        parser = new ArgParser(args3);
        parser.parse();
        assertEquals(6,Conf.poolSize);

        //change header size
        String[] args4 = {"-hs", "2"};
        parser = new ArgParser(args4);
        parser.parse();
        assertEquals(2,Conf.headerSize);

        String[] args5 = {"--header-size", "22"};
        parser = new ArgParser(args5);
        parser.parse();
        assertEquals(22,Conf.headerSize);






    }







}