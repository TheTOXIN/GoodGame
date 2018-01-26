package com.sharaga.gg.server;

import com.sharaga.gg.utill.Parse;
import com.sharaga.gg.utill.Rule;

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
        String data = Parse.build(Rule.STA, Parse.getMes(Parse.getStr(packet)));
        server.sender.sendOther(data);
        System.out.println(Parse.getMes(data));
    }
}
