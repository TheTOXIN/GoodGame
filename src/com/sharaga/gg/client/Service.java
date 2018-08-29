package com.sharaga.gg.client;

import com.sharaga.gg.utill.Parse;
import com.sharaga.gg.utill.Rule;
import com.sharaga.gg.utill.Settings;

public class Service {

    private Connector con;
    private Game game;

    public Service(Connector con, Game game) {
        this.con = con;
        this.game = game;
    }

    public void login() {
        con.send(Parse.build(Rule.CON, game.nick));
        String data = con.receive();
        Rule answer = Parse.getRule(data);
        con.isConnected = answer == Rule.TRU;
        if (answer == Rule.TRU) game.players.put(game.nick, Mapper.toPlayer(Parse.getMes(data)));
    }

    public void logout() {
        con.send(Parse.build(Rule.DIS, game.nick));
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
                    game.players.put(p.getName(), p);
                } else if (Rule.DIS == rule) {
                    game.players.remove(message);
                } else if (Rule.MAP == rule) {
                    game.world = message;
                } else if (Rule.STA == rule) {
                    Player newP = Mapper.toPlayer(message);
                    Player oldP = game.players.get(newP.getName());
                    oldP.setScore(newP.getScore());
                }
            }
        }).start();
    }

    public void informer() {
        if (!game.isSleep) {
            String message = Parse.buildDelimer(game.getPlayer().getName(), game.getPlayer().getState().toString());
            con.send(Parse.build(Rule.STA, message));
            sleep();
        }
    }

    public void sleep() {
        try {
            game.isSleep = true;
            Thread.sleep(Settings.PING);
            game.isSleep = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
