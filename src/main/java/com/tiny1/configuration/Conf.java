package com.tiny1.configuration;

import com.tiny1.model.Defaults;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that stores configurations.
 *
 * The purpose of this class is to store configurations in such a way
 * that every field can only be initialized once, either through
 * default initialization or through command-line arguments. To
 * achieve this, the class uses the singleton pattern along with
 * getters and setters that behave as follows:
 *
 * getters can only be used after makeImmutable() is called
 * setters can only be used before makeImmutable() is called
 */
public final class Conf {

    private int port;
    private int poolSize;
    private int headerSize;
    private String staticPath;
    private boolean TLS;
    private final Map<String, String> redirects;
    private String dtFormat;
    private boolean debug;

    private boolean isFinalized;

    private static final Conf INSTANCE = new Conf();

    private Conf() {
        this.port = Defaults.PORT;
        this.poolSize = Defaults.THREAD_POOL;
        this.headerSize = Defaults.MAX_HTTP_HEADER_SIZE;
        this.staticPath = Defaults.STATIC_PATH;
        this.TLS = Defaults.USE_TLS;
        this.redirects = new HashMap<>();
        this.dtFormat = Defaults.DATE_TIME_FORMAT;
        this.debug = Defaults.DEBUG;
    }


    // Getters

    public static int getPort() {
        isGetAllowed();
        return INSTANCE.port;
    }

    public static int getPoolSize() {
        isGetAllowed();
        return INSTANCE.poolSize;
    }

    public static int getHeaderSize() {
        isGetAllowed();
        return INSTANCE.headerSize;
    }

    public static String getStaticPath() {
        isGetAllowed();
        return INSTANCE.staticPath;
    }

    public static boolean isTLS() {
        isGetAllowed();
        return INSTANCE.TLS;
    }

    public static Map<String, String> getRedirects() {
        isGetAllowed();
        return new HashMap<>(INSTANCE.redirects);
    }

    public static String getDtFormat() {
        isGetAllowed();
        return INSTANCE.dtFormat;
    }

    public static boolean isDebug() {
        isGetAllowed();
        return INSTANCE.debug;
    }

    // Setters

    public static void setPort(int port) {
        isSetAllowed();
        INSTANCE.port = port;
    }

    public static void setPoolSize(int poolSize) {
        isSetAllowed();
        INSTANCE.poolSize = poolSize;
    }

    public static void setHeaderSize(int headerSize) {
        isSetAllowed();
        INSTANCE.headerSize = headerSize;
    }

    public static void setStaticPath(String staticPath) {
        isSetAllowed();
        INSTANCE.staticPath = staticPath;
    }

    public static void setTLS(boolean TLS) {
        isSetAllowed();
        INSTANCE.TLS = TLS;
    }

    public static void addRedirects(String from, String to) {
        isSetAllowed();
        INSTANCE.redirects.put(from,to);
    }

    public static void setDtFormat(String dtFormat) {
        isSetAllowed();
        INSTANCE.dtFormat = dtFormat;
    }

    public static void setDebug(boolean debug) {
        isSetAllowed();
        INSTANCE.debug = debug;
    }

    public void setFinalized(boolean finalized) {
        isSetAllowed();
        INSTANCE.isFinalized = finalized;
    }

    public static void makeImmutable() {
        INSTANCE.isFinalized = true;
    }

    private static void isGetAllowed() {
        if (!INSTANCE.isFinalized)
            throw new IllegalStateException("Getter fault. Configuration not finalized.");
    }

    private static void isSetAllowed() {
        if (INSTANCE.isFinalized)
            throw new IllegalStateException("Setter fault. Configuration is finalized");
    }


}
