package book.oioexample;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class PlainOioClient {

    public static void main(String[] args) {
        Socket socket;
        try {
            socket = new Socket("localhost", 8899);
            InputStream in = socket.getInputStream();
            byte[] bytes = new byte[in.available()];
            int read = in.read(bytes);
            if (read != -1) {
                System.out.println(new String(bytes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
