package d14.cau1.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Create by TranVanTu on 12/13/2021 8:53 PM
 * Desc:
 **/
public class Client {
    final int port;
    final InetAddress host;
    final Socket client;
    public Client(int port, InetAddress host) throws IOException {
        this.port = port;
        this.host = host;
        this.client = new Socket(host, port);

    }
    void execute() throws IOException {
        Scanner scanner = new Scanner(System.in);
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        while (true) {
            System.out.println("Nhap vao: ");
            int n = scanner.nextInt();
            dos.writeInt(n);
            System.out.println(dis.readUTF());
            if (dis.equals("dung")) {
                break;
            }
        }
        client.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(4444, InetAddress.getLocalHost());
        client.execute();
    }
}
