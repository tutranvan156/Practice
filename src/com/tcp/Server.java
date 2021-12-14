package com.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create by TranVanTu on 12/13/2021 3:29 PM
 * Desc:
 **/
public class Server {
    public int PORT;
    final ServerSocket serverSocket;
    final Socket client;
    public Server(int PORT) throws IOException {
        this.PORT = PORT;
        this.serverSocket = new ServerSocket(PORT);
        this.client = serverSocket.accept();
    }

    public void execute() throws IOException {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            while(true) {
                int sum = 0;
                for (int i = 0; i < 5; i++) {
                    sum += dis.readInt();
                }
                dos.writeUTF(String.valueOf(sum));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.close();
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(4444);
        server.execute();
    }
}
