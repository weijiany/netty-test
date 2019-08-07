package book.oioexample;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PlainOioServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            while (true) {
                Socket client = serverSocket.accept();
                new Thread(() -> {
                    try {
                        OutputStream out = client.getOutputStream();
                        out.write("hi".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
