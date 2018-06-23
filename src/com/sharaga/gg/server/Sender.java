package com.sharaga.gg.server;

import com.sharaga.gg.server.model.User;
import com.sharaga.gg.server.service.PlayerService;
import com.sharaga.gg.utill.Parse;
import com.sharaga.gg.utill.Rule;

import java.io.IOException;
import java.util.Map;

public class Sender {

    private Server server = Server.getInstance();

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
        server.users.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(user -> !user.getPlayer().equals(sender.getPlayer()))
                .forEach(user -> send(user, data));
    }

    public void sendAnother(User user) {
        server.game.players.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .forEach(p -> send(user, Parse.build(Rule.CON, PlayerService.string(p))));
    }

    public void sendAll(String data) {
        server.users.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .forEach(user -> send(user, data));
    }

}
