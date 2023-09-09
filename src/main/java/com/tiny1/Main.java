package com.tiny1;

import com.tiny1.configuration.Conf;
import com.tiny1.server.HttpServer;
import com.tiny1.server.HttpsServer;
import com.tiny1.util.ArgParser;
import com.tiny1.util.Console;

public class Main {
    public static void main(String[] args) {
        ArgParser parser = new ArgParser(args);
        parser.parse();

        Console.showLogo();
        Console.showSettings();

        if (Conf.isTLS())
            HttpsServer.start();
        else
            HttpServer.start();
    }
}
