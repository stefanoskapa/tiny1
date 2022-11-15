package com.tiny1.util;

public class Console {
    public static void showRequest(String request) {
        System.out.println("Request size: " + request.length() + " bytes");
        String[] lines = request.split("\r\n|\n|\r");
        for (int i = 0; i < lines.length; i++) {;
            if (i == 0)
                System.out.println("-> " + lines[i]);
            else
                System.out.println("   " + lines[i]);
        }
    }

    public static void showResponse(String response) {
        String[] lines = response.split("\r\n|\n|\r");
        for (int i = 0; i < lines.length; i++) {;
            if (i == 0)
                System.out.println("<- " + lines[i]);
            else
                System.out.println("   " + lines[i]);
        }
    }

    public static void showLogo() {
        System.out.println();
        System.out.println("   _/      _/                        _/");
        System.out.println("_/_/_/_/      _/_/_/    _/    _/  _/_/");
        System.out.println(" _/      _/  _/    _/  _/    _/    _/");
        System.out.println("_/      _/  _/    _/  _/    _/    _/");
        System.out.println(" _/_/  _/  _/    _/    _/_/_/    _/       v0.1");
        System.out.println("                          _/");
        System.out.println("                     _/_/\n");
    }
}
