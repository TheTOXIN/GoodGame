package com.sharaga.gg.client;

import com.sharaga.gg.utill.Rule;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class Connector {
    private DatagramSocket socket;
    private InetAddress ip;
    private int port;

    public boolean isConnected;

    public Connector(int port, String address) {
        try {
            this.port = port;
            this.ip = InetAddress.getByName(address);
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            JOptionPane.showMessageDialog(null, "YOU CONNECT TO PROBLEM...");
            System.out.println("Socket - ERROR");
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "YOU CONNECT TO PROBLEM...");
            System.out.println("Address - ERROR");
        }
    }

    public String receive() {
        byte[] data = new byte[4096];
        DatagramPacket packet = new DatagramPacket(data, data.length);

        try {
            socket.receive(packet);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "YOU CONNECT TO PROBLEM...");
            System.out.println("Receive - ERROR");
            return Rule.ERR.toString();
        }

        return new String(packet.getData());
    }

    public void send(final String message) {
        byte[] data = message.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);

        try {
            socket.send(packet);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "YOU CONNECT TO PROBLEM...");
            System.out.println("Send - ERROR");
        }
    }
}
