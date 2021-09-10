package ru.job4j.io.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.logger.UsageLog4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG
            = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            final StringBuilder msg = new StringBuilder();
            while (!(msg.toString().equals("Exit"))) {
                msg.setLength(0);
                final Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))
                ) {
                    in.lines().takeWhile(line -> !line.isEmpty())
                            .forEach(line -> {
                                System.out.println(line);
                                if (line.contains("GET/?msg")) {
                                    msg.append(line, line.indexOf("=") + 1,
                                            line.lastIndexOf(" "));
                                }
                            });
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (msg.toString().equals("Hello")) {
                        out.write("Hello, dear friend!".getBytes());
                    } else {
                        out.write(msg.toString().getBytes());
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in ServerSocket", e);
        }
    }
}
