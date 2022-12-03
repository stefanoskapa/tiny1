package com.tiny1.misc.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {

    public static void sendGET(String uri) throws IOException {
        URL obj = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.getResponseCode();
    }
}
