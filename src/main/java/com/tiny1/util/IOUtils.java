package com.tiny1.util;

import com.tiny1.model.Conf;
import com.tiny1.model.Defaults;

import java.io.*;

public class IOUtils {

    public static void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[Defaults.BUFFER_SIZE];
        int lengthRead;
        while ((lengthRead = input.read(buffer, 0, Defaults.BUFFER_SIZE)) > 0)
            output.write(buffer, 0, lengthRead);
        output.flush();
    }

    public static InputStream getResource(String uri) {
        try {
            return new DataInputStream(new FileInputStream(Conf.staticPath + uri));
        } catch (FileNotFoundException e) {
            return null;
        }
    }


}