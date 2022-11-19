package com.tiny1.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Console {
    public static void showRequest(String request) {
        if (request != null) {
            String[] lines = request.split("\r\n|\n|\r");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            System.out.println(LocalDateTime.now().format(formatter) + " -> " + lines[0]);
        }
    }

    public static void showLogo() {
        System.out.println();
        System.out.println("    _/      _/                        _/");
        System.out.println(" _/_/_/_/      _/_/_/    _/    _/  _/_/");
        System.out.println("  _/      _/  _/    _/  _/    _/    _/");
        System.out.println(" _/      _/  _/    _/  _/    _/    _/");
        System.out.println("  _/_/  _/  _/    _/    _/_/_/    _/       v0.1");
        System.out.println("                           _/");
        System.out.println("                      _/_/\n");
    }
}
