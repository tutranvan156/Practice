package d14.cau1.udp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Create by TranVanTu on 12/13/2021 9:10 PM
 * Desc:
 **/
public class Client {
    final int port;
    final InetAddress host;
    final DatagramSocket client;
    public Client(int port, InetAddress host) throws SocketException {
        this.host = host;
        this.port = port;
        this.client = new DatagramSocket();
    }
    void execute() throws IOException {
        byte[] receiveByte = new byte[255];
        DatagramPacket receivePacket = new DatagramPacket(receiveByte, receiveByte.length);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = "";
            byte[] sendByte = new byte[255];
            for (int i = 0; i < 5; i++) {
                int n = scanner.nextInt();
                sendByte = String.valueOf(n).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendByte, sendByte.length, host, port);
                client.send(sendPacket);
            }
            client.receive(receivePacket);
            String result = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println(result);
            if (result.equals("dung")) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(4444, InetAddress.getLocalHost());
        client.execute();
    }
}
