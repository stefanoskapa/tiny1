package com.tiny1.util;

import com.tiny1.model.Conf;
import com.tiny1.model.Request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Console {
    public static void log(Request requestObject, String response) {
        if (requestObject != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime now = LocalDateTime.now();
            long responseTime = ChronoUnit.MILLIS.between(requestObject.getTimestamp(), now);
            System.out.println(requestObject.getTimestamp().format(formatter) +
                    " -> " + requestObject.getMethod() +
                    " " + requestObject.getUri() +
                    " " + requestObject.getHttpTag() +
                    "\n" + now.format(formatter) + " <- " + response +
                    " (" + responseTime + "ms)");
        }
    }

    public static void showLogo() {
        System.out.println("\n\n     _/      _/                        _/");
        System.out.println("  _/_/_/_/      _/_/_/    _/    _/  _/_/");
        System.out.println("   _/      _/  _/    _/  _/    _/    _/");
        System.out.println("  _/      _/  _/    _/  _/    _/    _/");
        System.out.println("   _/_/  _/  _/    _/    _/_/_/    _/       v0.1");
        System.out.println("                            _/");
        System.out.println("                       _/_/\n");
    }

    public static void showSettings() {
        System.out.println("\nPort:.................." + Conf.port);
        System.out.println("TLS:..................." + (Conf.TLS ? "ON" : "OFF"));
        System.out.println("Pool Size:............." + Conf.poolSize);
        System.out.println("Max Header Size:......." + Conf.headerSize);
        System.out.println("Document Root:........." + Conf.staticPath + "\n");
    }

    public static void showHelp() {
        System.out.println("\n-p, --port              Port number");
        System.out.println("-ps, --pool-size        Thread Pool Size");
        System.out.println("-hs, --header-size      Maximum request-header size in bytes");
        System.out.println("-dr, --document-root    Document Root folder");
        System.out.println("-v, --version           Shows version of Tiny1");
        System.out.println("--tls                   Use https with JVM defaults");
        System.out.println("-h, --help              Shows this page\n");
    }
}
