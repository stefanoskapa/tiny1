package com.tiny1.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class for opening a raw connection and sending stdin
 * (for testing purposes)
 * [CR] -> Carriage return
 * [LF] -> Line feed
 */
public class RawConnection {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 8000;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection() throws IOException {
        Socket clientSocket = new Socket(HOSTNAME, PORT);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.print(msg);
        out.flush();
        return in.readLine();
    }

    public static void main(String[] args) {

        try {
            var con = new RawConnection();
            con.startConnection();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("-> ");
                String msg = scanner.nextLine();
                msg = msg.replace("[CR]", "\r").replace("[LF]", "\n");
                String response = con.sendMessage(msg);
                System.out.println("<- " + response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
