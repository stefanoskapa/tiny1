package com.tiny1.model;


public interface Response {
    String CRLF = "\r\n";
    String OK = "HTTP/1.0 200 OK" + CRLF;
    String BAD_REQUEST = "HTTP/1.0 400 Bad Request" + CRLF;
    String NOT_FOUND = "HTTP/1.0 404 Not Found" + CRLF;
    String INTERNAL_SERVER_ERROR = "HTTP/1.0 500 Internal Server Error" + CRLF;
    String METHOD_NOT_ALLOWED = "HTTP/1.0 501 Not Implemented" + CRLF;

}

