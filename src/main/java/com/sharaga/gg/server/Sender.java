package com.sharaga.gg.server;

import com.sharaga.gg.utill.Parse;

import java.io.IOException;

public class Sender {
    private Server server;

    public Sender(Server server) {
        this.server = server;
    }

    public void send(User user, String message) {
        byte[] data = message.getBytes();

        user.getPacket().setData(data);
        user.getPacket().setLength(data.length);

        try {
            server.socket.send(user.getPacket());
        } catch (IOException e) {
            System.out.println("Send - ERROR");
        }
    }

    public void sendOther(String data) {
        for (User user : server.users) {
            if (!Parse.getName(data).equals(user.getName())) {
                send(user, data);
            }
        }
    }

    public void sendAll(String message) {
        for (User user : server.users) {
            send(user, message);
        }
    }
}
