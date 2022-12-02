package com.tiny1.util;

import com.tiny1.model.Conf;
import com.tiny1.model.Defaults;

import java.io.*;

public class IOUtils {
    /**
     * Copies efficiently InputStream to OutputStream
     * BUFFER_SIZE of 8192 seems to perform well on average
     * @param input InputStream object
     * @param output OutputStream object
     * @throws IOException
     */
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
        } catch (Exception e) {
            return null;
        }
    }


}