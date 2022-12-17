package com.tiny1.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Objects;

public class Request {

    private String method;
    private String uri;
    private final String requestString;
    private final OutputStream output;
    private InputStream input;
    private String contentType;
    private final LocalDateTime timestamp;

    private String httpTag;

    public Request(String requestString, OutputStream output) {
        this.requestString = Objects.requireNonNull(requestString);
        this.output = Objects.requireNonNull(output);
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

    public OutputStream getOutput() {
        return output;
    }

    public InputStream getInput() {
        return input;
    }

    public void setInput(InputStream input) {
        this.input = input;
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

}
