package com.tiny1.handlers;

import com.tiny1.model.Handler;
import com.tiny1.model.Request;

import java.io.InputStream;
import java.util.Properties;

public class ContentTypeHandler extends Handler {
    public ContentTypeHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(String request, Request requestObject)  {

        try (InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("mime.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String extension = getExtension(requestObject.getUri());
            requestObject.setContentType(prop.getProperty(extension));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            requestObject.setContentType("Content-type: text/html\r\n");
        }
        return true;

    }

    private String getExtension(String string) {
        String fileExtension = "";
        String[] temp = string.split("\\.");
        if (temp.length > 1)
            fileExtension = temp[temp.length - 1];

        return fileExtension;
    }
}
