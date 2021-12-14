package com.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Create by TranVanTu on 12/13/2021 3:29 PM
 * Desc:
 **/
public class Client {
    public final int port;
    public final InetAddress host;
    public final Socket client;
    public Client(InetAddress host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.client = new Socket(host, port);
    }

    public void execute() throws IOException {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            int[] arr = new int[5];
            while (true) {
                for (int i = 0; i < 5; i++) {
                    arr[i] = scanner.nextInt();
                    dos.writeInt(arr[i]);
                }
                System.out.println(dis.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(InetAddress.getLocalHost(), 4444);
        client.execute();
    }
}
