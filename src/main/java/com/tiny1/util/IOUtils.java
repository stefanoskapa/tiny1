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
        try {
            return new DataInputStream(new FileInputStream(Conf.staticPath + uri));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}