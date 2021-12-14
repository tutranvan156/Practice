package com.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

/**
 * Create by TranVanTu on 12/13/2021 9:56 PM
 * Desc:
 **/
public class Respond implements Runnable{

    DatagramSocket socket;
    DatagramPacket receivePacket;
    public Respond(DatagramSocket socket, DatagramPacket receivePacket) {
        this.socket = socket;
        this.receivePacket = receivePacket;
    }
    @Override
    public void run() {
        byte[] sendByte = new byte[255];
        String n = new String(receivePacket.getData(), 0, receivePacket.getLength());
        sendByte = n.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendByte, sendByte.length, receivePacket.getAddress(), receivePacket.getPort());
        try {
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
