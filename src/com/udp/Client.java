package com.udp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Create by TranVanTu on 12/13/2021 6:29 AM
 * Desc:
 **/
public class Client {
    public final int port;
    public final InetAddress host;
    public final DatagramSocket client;

    public Client(int port, InetAddress host) throws SocketException {
        this.port = port;
        this.host = host;
        this.client = new DatagramSocket();
    }

    public void execute() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //send
            byte[] sendByte;
            System.out.println("Vui long nhap vao 1 so: ");
            int n = scanner.nextInt();
            sendByte = String.valueOf(n).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendByte, sendByte.length, host, port);
            client.send(sendPacket);
            //receive
            byte[] receiveByte = new byte[255];
            DatagramPacket receivePacket = new DatagramPacket(receiveByte, receiveByte.length);
            client.receive(receivePacket);
            String result = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println(result);
        }
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        Client client = new Client(6666, InetAddress.getLocalHost());
        try {
            client.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
