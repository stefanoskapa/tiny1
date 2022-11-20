package com.tiny1.misc;

import com.tiny1.misc.util.HttpRequest;
import com.tiny1.model.Defaults;

import java.io.IOException;

public class LatencyTest {

    public static void main(String[] args) {

        for (int i = 0; i<5;i++) {

            try {
                long start = System.currentTimeMillis();
                HttpRequest.sendGET("http://localhost:" + Defaults.PORT + "/images/lady-in-yoga.jpg");
                System.out.println((System.currentTimeMillis() - start) + "ms");
                Thread.sleep(2000);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
