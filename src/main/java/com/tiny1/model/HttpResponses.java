package com.tiny1.model;


public interface HttpResponses {
    String CRLF = "\r\n";
    String OK = "HTTP/1.0 200 OK";
    String BAD_REQUEST = "HTTP/1.0 400 BadRequest";
    String NOT_FOUND = "HTTP/1.0 404 NotFound";
    String INTERNAL_SERVER_ERROR = "HTTP/1.0 500 InternalServerError";
    String METHOD_NOT_ALLOWED = "HTTP/1.0 501 NotImplemented";
    String MOVED_PERMANENTLY = "HTTP/1.0 301 MovedPermanently";

}

