package com.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Create by TranVanTu on 12/13/2021 6:29 AM
 * Desc:
 **/
public class Server implements Runnable {
    public final int port;
    final DatagramSocket serverSocket;

    public Server(int port) throws SocketException {
        this.port = port;
        this.serverSocket = new DatagramSocket(port);
    }
    @Override
    public void run() {
        byte[] receiveByte = new byte[255];
        DatagramPacket receivePacket = new DatagramPacket(receiveByte, receiveByte.length);
        while (true) {
            try {
                serverSocket.receive(receivePacket);
                new Thread(new Respond(serverSocket, receivePacket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Thread(new Server(6666)).start();
    }
}
