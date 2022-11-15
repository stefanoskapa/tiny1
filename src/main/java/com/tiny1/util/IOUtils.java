package com.tiny1.util;

import java.io.*;

public class IOUtils {
    private static final String STATIC_PATH = System.getProperty("user.dir") + "/static";

    public static void copy(InputStream in, OutputStream out) throws IOException {
        int value;
        while ((value = in.read()) != -1) {
            out.write(value);
        }
    }

    public static InputStream getResource(String uri) {
        String fullFilePath = (STATIC_PATH + uri).replace("\\","/");
        File initialFile = new File(fullFilePath);
        try {
            return new DataInputStream(new FileInputStream(initialFile));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}