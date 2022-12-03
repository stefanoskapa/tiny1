package com.tiny1.util;

import com.tiny1.model.Conf;
import com.tiny1.model.Request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.MILLIS;


public class Console {
    public static void log(Request request, String response) {
        if (request == null)
            return;
        var now = LocalDateTime.now();
        var format = DateTimeFormatter.ofPattern(Conf.dtFormat);
        long responseTime = MILLIS.between(request.getTimestamp(), now);

        String output = request.getTimestamp().format(format) +
                " -> " + request.getMethod() +
                " " + request.getUri() +
                " " + request.getHttpTag() +
                "\n" + now.format(format) +
                " <- " + response +
                " (" + responseTime + "ms)";
        System.out.println(output);
    }

    public static void showLogo() {
        System.out.println();
        System.out.println("     _/      _/                        _/");
        System.out.println("  _/_/_/_/      _/_/_/    _/    _/  _/_/");
        System.out.println("   _/      _/  _/    _/  _/    _/    _/");
        System.out.println("  _/      _/  _/    _/  _/    _/    _/");
        System.out.println("   _/_/  _/  _/    _/    _/_/_/    _/       v0.1");
        System.out.println("                            _/");
        System.out.println("                       _/_/");
        System.out.println();
    }

    public static void showSettings() {
        System.out.println();
        System.out.println("Port:.................." + Conf.port);
        System.out.println("TLS:..................." + (Conf.TLS ? "Enabled" : "Disabled"));
        System.out.println("Pool Size:............." + Conf.poolSize);
        System.out.println("Max Header Size:......." + Conf.headerSize);
        System.out.println("Document Root:........." + Conf.staticPath);
        System.out.println();
        if (!Conf.redirects.isEmpty()) {
            System.out.println("--------- Redirects ---------");
            System.out.println(Conf.redirects);
        }
        System.out.println();
    }

    public static void showHelp() {
        System.out.println();
        System.out.println("-p, --port              Port number");
        System.out.println("-ps, --pool-size        Thread Pool Size");
        System.out.println("-hs, --header-size      Maximum request-header size in bytes");
        System.out.println("-dr, --document-root    Document Root folder");
        System.out.println("-v, --version           Shows version of Tiny1");
        System.out.println("--tls                   Use https with JVM defaults");
        System.out.println("-r, --redirect          301 Redirect. Specify two urls");
        System.out.println("-h, --help              Shows this page");
        System.out.println();
    }
}
