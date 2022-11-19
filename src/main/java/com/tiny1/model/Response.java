package com.tiny1.model;


public interface Response {
    String CRLF = "\r\n";
    String OK = "HTTP/1.0 200 OK" + CRLF;
    String BAD_REQUEST = "HTTP/1.0 400 BadRequest" + CRLF;
    String NOT_FOUND = "HTTP/1.0 404 NotFound" + CRLF;
    String INTERNAL_SERVER_ERROR = "HTTP/1.0 500 InternalServerError" + CRLF;
    String METHOD_NOT_ALLOWED = "HTTP/1.0 501 NotImplemented" + CRLF;

}

