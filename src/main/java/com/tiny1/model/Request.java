package com.tiny1.model;

import java.io.InputStream;
import java.io.OutputStream;

public class Request {

    private String method;
    private String uri;
   // private String httpVersion;
    private OutputStream output;
    private InputStream input;
    private String contentType;

    public String getMethod() {
        return method;
    }

    public OutputStream getOutput() {
        return output;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
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

    //public String getHttpVersion() {
  //      return httpVersion;
   // }

   // public void setHttpVersion(String httpVersion) {
    //    this.httpVersion = httpVersion;
   // }
}
