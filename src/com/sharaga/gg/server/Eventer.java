package com.sharaga.gg.server;

import com.sharaga.gg.utill.*;

import java.net.DatagramPacket;

public class Eventer {
    private Server server;

    public Eventer(Server server) {
        this.server = server;
    }

    public void eventNewConnection(DatagramPacket packet) {
        String name = Parse.getMes(Parse.getStr(packet));
        User user = new User(name, packet);

        if (!server.users.contains(user)) {
            server.users.add(user);
            server.sender.send(user, Rule.TRU.name());
            System.out.println("Connection - " + user);
        } else {
            server.sender.send(user, Rule.FLS.name());
        }
    }

    public void eventSendMessage(DatagramPacket packet) {
        String data = Parse.build(Rule.MES, Parse.getMes(Parse.getStr(packet)));
        server.sender.sendAll(data);
        System.out.println(Parse.getMes(data));
    }

    public void updateState(DatagramPacket packet) {
        String message = Parse.getMes(packet);

        Player p = Mapper.toPlayer(message);
        State state = p.getState();

        if (state == State.UP) {
            p.stepUp();
        } else if (state == State.DOWN) {
            p.stepDown();
        } else if (state == State.LEFT) {
            p.stepLeft();
        } else if (state == State.RIGHT) {
            p.stepRight();
        }
        message = Mapper.toString(p);

        String data = Parse.build(Rule.UPD, message);
        server.sender.sendAll(data);
        System.out.println(Parse.getMes(data));
    }
}
