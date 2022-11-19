package com.tiny1.model;

public interface Defaults {
    int MAX_HTTP_HEADER_SIZE = 8000;
    int THREAD_POOL = 5;
    int PORT = 8000;
    String STATIC_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") + "static";

    String VERSION = "Tiny1 v0.1";
}
