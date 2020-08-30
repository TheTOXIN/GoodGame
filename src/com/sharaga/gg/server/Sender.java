package com.sharaga.gg.server;

import com.sharaga.gg.server.model.User;

import java.io.IOException;

public class Sender {

    private Server server;

    public Sender(Server server) {
        this.server = server;
    }

    public void send(User user, String data) {
        byte[] bytes = data.getBytes();
        user.getPacket().setData(bytes);
        user.getPacket().setLength(bytes.length);

        try {
            server.socket.send(user.getPacket());
        } catch (IOException e) {
            System.out.println("Send - ERROR");
        }
    }

    public void sendOther(User sender, String data) {
        for (User user : Server.users) {
            if (user.getId() != sender.getId()) {
                send(user, data);
            }
        }
    }

    public void sendAll(String data) {
        for (User user : Server.users) {
            send(user, data);
        }
    }
}
