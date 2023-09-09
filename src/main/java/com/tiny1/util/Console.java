package com.tiny1.util;

import com.tiny1.configuration.Conf;
import com.tiny1.model.Request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.MILLIS;


public class Console {
    public static void log(Request request, String response) {
        if (request == null)
            return;
        var now = LocalDateTime.now();
        var format = DateTimeFormatter.ofPattern(Conf.getDtFormat());
        long responseTime = MILLIS.between(request.getTimestamp(), now);

        String output = "%s -> %s %s %s (%s)\n%s <- %s (%sms)";
        System.out.printf(output, request.getTimestamp().format(format),
                request.getMethod(), request.getUri(), request.getHttpTag(),
                request.getSocket().getInetAddress().getHostAddress(),
                now.format(format), response, responseTime);
    }

    public static void logErr(Exception e) {
        if (Conf.isDebug())
            e.printStackTrace();
        else
            System.out.println(e.getMessage());
    }

    public static void showLogo() {
        System.out.println();
        System.out.println("     _/      _/                        _/");
        System.out.println("  _/_/_/_/      _/_/_/    _/    _/  _/_/");
        System.out.println("   _/      _/  _/    _/  _/    _/    _/");
        System.out.println("  _/      _/  _/    _/  _/    _/    _/");
        System.out.println("   _/_/  _/  _/    _/    _/_/_/    _/");
        System.out.println("                            _/");
        System.out.println("                       _/_/");
        System.out.println();
    }

    public static void showSettings() {
        System.out.println();
        System.out.println("Port:.................." + Conf.getPort());
        System.out.println("TLS:..................." + (Conf.isTLS() ? "Enabled" : "Disabled"));
        System.out.println("Pool Size:............." + Conf.getPoolSize());
        System.out.println("Max Header Size:......." + Conf.getHeaderSize());
        System.out.println("Document Root:........." + Conf.getStaticPath());
        System.out.println();
        if (!Conf.getRedirects().isEmpty()) {
            System.out.println("--------- Redirects ---------");
            System.out.println(Conf.getRedirects());
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
