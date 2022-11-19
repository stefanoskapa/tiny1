package com.tiny1.util;

import com.tiny1.model.Conf;
import com.tiny1.model.Request;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Console {
    public static void log(Request requestObject, String response) {
        if (requestObject != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            System.out.println(requestObject.getTimestamp().format(formatter) +
                    " -> " + requestObject.getMethod() +
                    " " + requestObject.getUri() +
                    " " + requestObject.getHttpTag() +
                    "\n" + LocalDateTime.now().format(formatter) + " <- " + response);
        }
    }

    public static void showLogo() {
        System.out.println("\n    _/      _/                        _/");
        System.out.println(" _/_/_/_/      _/_/_/    _/    _/  _/_/");
        System.out.println("  _/      _/  _/    _/  _/    _/    _/");
        System.out.println(" _/      _/  _/    _/  _/    _/    _/");
        System.out.println("  _/_/  _/  _/    _/    _/_/_/    _/       v0.1");
        System.out.println("                           _/");
        System.out.println("                      _/_/\n");
    }

    public static void showSettings() {
        System.out.println("\nPort:.................." + Conf.port);
        System.out.println("Pool size:............." + Conf.poolSize);
        System.out.println("Max Header size:......." + Conf.headerSize);
        System.out.println("Resource directory:...." + Conf.staticPath+"\n");
    }

    public static void showHelp() {
        System.out.println("\n-p, --port            Port number");
        System.out.println("-ps, --pool-size      Thread Pool Size");
        System.out.println("-hs, --header-size    Maximum request-header size in bytes");
        System.out.println("-sp, --static-path    Folder to expose");
        System.out.println("-v, --version         Shows version of Tiny1");
        System.out.println("-h, --help            Shows this page\n");
    }
}
