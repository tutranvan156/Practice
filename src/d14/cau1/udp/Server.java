package d14.cau1.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

/**
 * Create by TranVanTu on 12/13/2021 9:10 PM
 * Desc:
 **/
public class Server {
    final int port;
    final DatagramSocket server;
    public Server(int port) throws SocketException {
        this.port = port;
        server = new DatagramSocket(port);
    }
    void execute() throws IOException {
        byte[] receiveByte = new byte[255];
        DatagramPacket receivePacket = new DatagramPacket(receiveByte, receiveByte.length);
        int number = (int) (Math.random() * 100);
        System.out.println(number);
        while (true) {
            int n = 0;
            for (int i = 0; i < 5; i++) {
                server.receive(receivePacket);
                n += Integer.parseInt(new String(receivePacket.getData(), 0, receivePacket.getLength()));
            }
            byte[] sendByte;
            String result = null;
            if (n == number) {
                 result = "Chuc mung";
            } else if (n < number) {
                result = "nho hon so ngau nhien";
            } else if (n > number) {
                result = "lon hon so ngau nhien";
            }
            sendByte = result.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendByte, sendByte.length, receivePacket.getAddress(), receivePacket.getPort());
            server.send(sendPacket);
        }
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server(4444);
        server.execute();
    }


}
