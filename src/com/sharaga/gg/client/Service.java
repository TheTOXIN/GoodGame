package com.sharaga.gg.client;

import com.sharaga.gg.utill.Parse;
import com.sharaga.gg.utill.Rule;
import com.sharaga.gg.utill.Const;
import com.sharaga.gg.utill.State;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.sharaga.gg.utill.Parse.buildDelimer;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class Service {

    private Connector con;
    private Room room;

    public Service(Connector con, Room room) {
        this.con = con;
        this.room = room;
    }

    public void login() throws Exception {
        con.send(Parse.build(Rule.CON, room.nick));

        CompletableFuture<String> future = supplyAsync(() -> con.receive());
        sleep(1000);

        if (!future.isDone()) return;

        String data = future.get();
        Rule answer = Parse.getRule(data);

        if (answer == Rule.TRU) {
            Player player = Mapper.toPlayer(Parse.getMes(data));

            room.players.put(room.nick, player);
            con.isConnected = true;
        }
    }

    public void logout() {
        con.send(Parse.build(Rule.DIS, room.nick));
    }

    public void start() {
        listener();
        informer(State.NONE);
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
                    room.players.put(newP.getName(), newP);
                }
            }
        }).start();
    }

    public void informer(State state) {
        if (!room.isSleep) {
            String nick = room.getPlayer().getName();
            String message = buildDelimer(nick, state.toString());

            con.send(Parse.build(Rule.STA, message));

            room.isSleep = true;
            sleep(Const.PING);
            room.isSleep = false;
        }
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
