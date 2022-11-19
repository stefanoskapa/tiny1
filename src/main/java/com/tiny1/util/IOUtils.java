package com.tiny1.util;

import com.tiny1.model.Conf;

import java.io.*;

public class IOUtils {

    public static void copy(InputStream input, OutputStream output) throws IOException {
        int value;
        while ((value = input.read()) != -1) {
            output.write(value);
        }
    }

    public static InputStream getResource(String uri) {
        String fullFilePath = (Conf.staticPath + uri).replace("\\","/");
        File initialFile = new File(fullFilePath);
        try {
            return new DataInputStream(new FileInputStream(initialFile));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}