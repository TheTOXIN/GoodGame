package com.sharaga.gg.client;

import com.sharaga.gg.utill.Parse;
import com.sharaga.gg.utill.Rule;
import com.sharaga.gg.utill.Const;

public class Service {

    private Connector con;
    private Room room;

    public Service(Connector con, Room room) {
        this.con = con;
        this.room = room;
    }

    public void login() {
        con.send(Parse.build(Rule.CON, room.nick));
        String data = con.receive();
        Rule answer = Parse.getRule(data);
        con.isConnected = answer == Rule.TRU;
        if (answer == Rule.TRU) room.players.put(room.nick, Mapper.toPlayer(Parse.getMes(data)));
    }

    public void logout() {
        con.send(Parse.build(Rule.DIS, room.nick));
    }

    public void start() {
        listener();
        informer();
    }

    public void listener() {
        new Thread(() -> {
            while (con.isConnected) {
                String data = con.receive();
                String message = Parse.getMes(data);
                Rule rule = Parse.getRule(data);

                if (Rule.CON == rule) {
                    Player p = Mapper.toPlayer(message);
                    room.players.put(p.getName(), p);
                } else if (Rule.DIS == rule) {
                    room.players.remove(message);
                } else if (Rule.MAP == rule) {
                    room.world = message;
                } else if (Rule.STA == rule) {
                    Player newP = Mapper.toPlayer(message);
                    Player oldP = room.players.get(newP.getName());
                    oldP.setScore(newP.getScore());
                }
            }
        }).start();
    }

    public void informer() {
        if (!room.isSleep) {
            String message = Parse.buildDelimer(
                room.getPlayer().getName(),
                room.getPlayer().getState().toString()
            );

            con.send(Parse.build(Rule.STA, message));
            sleep();
        }
    }

    public void sleep() {
        try {
            room.isSleep = true;
            Thread.sleep(Const.PING);
            room.isSleep = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
