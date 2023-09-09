package com.tiny1.util;

import com.tiny1.configuration.Conf;
import com.tiny1.model.Defaults;

import java.io.*;
import java.net.Socket;

public class IOUtils {
    /**
     * Copies efficiently InputStream to OutputStream
     *
     * @param input  InputStream object
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
            return new DataInputStream(new FileInputStream(Conf.getStaticPath() + uri));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Waits until data is read from the input stream.
     *
     * @param socket A socket object
     * @return String with the data that has been read
     * @throws IOException
     * @throws NullPointerException
     * @throws IllegalStateException
     */
    public static String parseRequest(Socket socket) throws IOException {

        byte[] header = new byte[Conf.getHeaderSize() + 1];
        InputStream inputStream = socket.getInputStream();

        int size = inputStream.read(header);
        if (size == -1)
            throw new IllegalStateException("No data");
        if (size == Conf.getHeaderSize() + 1)
            throw new IllegalStateException("Header size exceeded");

        return new String(header, 0, size);
    }


}