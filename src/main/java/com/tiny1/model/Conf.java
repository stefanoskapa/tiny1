package com.tiny1.model;

import java.util.HashMap;
import java.util.Map;

public class Conf {

    public static int port = Defaults.PORT;
    public static int poolSize = Defaults.THREAD_POOL;
    public static int headerSize = Defaults.MAX_HTTP_HEADER_SIZE;
    public static String staticPath = Defaults.STATIC_PATH;

    public static boolean TLS = Defaults.USE_TLS;

    public static final Map<String, String> redirects = new HashMap<>();
}
