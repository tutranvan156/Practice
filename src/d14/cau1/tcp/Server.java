package d14.cau1.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create by TranVanTu on 12/13/2021 8:53 PM
 * Desc:
 **/
public class Server {
    final int port;
    final ServerSocket serverSocket;
    final Socket client;
    public Server(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        this.client = serverSocket.accept();
    }
    void execute() throws IOException {
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        int number = (int)(Math.random() * 100);
        System.out.println(number);
        while (true) {
            int n = dis.readInt();
            if (n == number) {
                dos.writeUTF("Chuc mung");
                break;
            } else if (n < number) {
                dos.writeUTF("nho hon so ngau nhien");
            } else if (n > number) {
                dos.writeUTF("lon hon so ngau nhien");
            }
        }
        client.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(4444);
        server.execute();
    }

}
