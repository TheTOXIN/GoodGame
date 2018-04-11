package com.sharaga.gg.client;

import java.io.IOException;
import java.net.*;

/**
 * Класс для соединения с сервером,
 * а так-же для передачи и принятия сообщений
 */
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

            this.isConnected = false;
        } catch (SocketException e) {
            System.out.println("Socket - ERROR");
        } catch (UnknownHostException e) {
            System.out.println("Address - ERROR");
        }
    }

    public String receive() {
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);

        try {
            socket.receive(packet);
        } catch (IOException e) {
            System.out.println("Receive - ERROR");
            return "ERR";
        }

        return new String(packet.getData());
    }

    public void send(final String message) {
        byte[] data = message.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);

        try {
            socket.send(packet);
        } catch (IOException e) {
            System.out.println("Send - ERROR");
        }
    }
}
