package com.tiny1.handlers;

import com.tiny1.model.Request;
import com.tiny1.model.Response;

import java.io.InputStream;
import java.util.Properties;

public class ContentTypeHandler extends Handler {
    public ContentTypeHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean handleImpl(Request request, Response response)  {

        try (InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("mime.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String extension = getExtension(request.getUri());
            request.setContentType(prop.getProperty(extension)); // should be in Response Object
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setContentType("text/html\r\n");
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
