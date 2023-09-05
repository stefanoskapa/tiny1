package com.tiny1.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Objects;

public class Request {

    private String method;
    private String uri;
    private final String requestString;
    private Socket socket;
    private String contentType;
    private final LocalDateTime timestamp;
    private String httpTag;

    public Request(String requestString, Socket socket) {
        this.requestString = Objects.requireNonNull(requestString);
        this.socket = Objects.requireNonNull(socket);
        this.timestamp = LocalDateTime.now();
    }

    public String getRequestString() {
        return requestString;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getHttpTag() {
        return httpTag;
    }

    public void setHttpTag(String httpTag) {
        this.httpTag = httpTag;
    }

    public String getMethod() {
        return method;
    }





    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", uri='" + uri + '\'' +
                ", requestString='" + requestString.replace("\r", "[CR]").replace("\n", "[LF]") + '\'' +
                ", contentType='" + contentType + '\'' +
                ", timestamp=" + timestamp +
                ", httpTag='" + httpTag + '\'' +
                '}';
    }
}
