package com.tiny1.model;


public interface Response {

    String OK = "HTTP/1.0 200 OK\r\n";
    String NOT_FOUND = "HTTP/1.0 404 NotFound\r\n";
    String INTERNAL_SERVER_ERROR = "HTTP/1.0 500 InternalServerError\r\n";
    String METHOD_NOT_ALLOWED = "HTTP/1.0 405 Method Not Allowed\r\n";



}

