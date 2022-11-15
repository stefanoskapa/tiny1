package com.tiny1.util;

public class Console {
    public static void showRequest(String request) {
        showIOWithIndentation(request, "-> ");
    }

    public static void showResponse(String response) {
        showIOWithIndentation(response, "<- ");
    }

    private static void showIOWithIndentation(String string, String prefix) {
        String[] lines = string.split("\r\n|\n|\r");
        for (int i = 0; i < lines.length; i++) {;
            if (i == 0)
                System.out.println(prefix + lines[i]);
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
