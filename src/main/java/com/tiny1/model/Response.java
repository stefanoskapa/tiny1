package com.tiny1.model;


public interface Response {
    String OK = "HTTP/1.0 200 OK\r\n";
    String BAD_REQUEST = "HTTP/1.0 400 Bad Request\r\n";
    String NOT_FOUND = "HTTP/1.0 404 Not Found\r\n";
    String INTERNAL_SERVER_ERROR = "HTTP/1.0 500 Internal Server Error\r\n";
    String METHOD_NOT_ALLOWED = "HTTP/1.0 501 Not Implemented\r\n";
    String CRLF = "\r\n";

}

