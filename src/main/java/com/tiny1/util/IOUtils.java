package com.tiny1.util;

import com.tiny1.model.Conf;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class IOUtils {

    public static void copy(InputStream input, OutputStream output) throws IOException {
        final ReadableByteChannel inputChannel = Channels.newChannel(input);
        final WritableByteChannel outputChannel = Channels.newChannel(output);
        fastChannelCopy(inputChannel, outputChannel);
    }

    public static InputStream getResource(String uri) {
        try {
            return new DataInputStream(new FileInputStream(Conf.staticPath + uri));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private static void fastChannelCopy(ReadableByteChannel input, WritableByteChannel output) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (input.read(buffer) != -1) {
            buffer.flip();
            output.write(buffer);
            buffer.compact();
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            output.write(buffer);
        }
    }

}