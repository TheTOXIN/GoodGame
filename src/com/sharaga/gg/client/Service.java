package com.sharaga.gg.client;

import com.sharaga.gg.utill.Parse;
import com.sharaga.gg.utill.Rule;
import com.sun.xml.internal.ws.handler.ServerSOAPHandlerTube;

/**
 * Основной класс для управления пакетами
 */
public class Service {
    private Connector con;
    private Game game;

    public Service(Connector con, Game game) {
        this.con = con;
        this.game = game;
    }

    public void login() {
        con.send(Parse.build(Rule.CON, game.player.getName()));

        Thread ping = new Thread(() -> {
            Rule answer = Parse.getRule(con.receive());
            con.isConnected = answer == Rule.TRU;
        });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!con.isConnected) ping.interrupt();
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

                if (Rule.STA == rule) {
                    System.out.println(message);
                    Player p = Mapper.toPlayer(message);
                    game.players.put(p.getName(), p);
                }
            }
        }).start();
    }

    public void informer() {
        String message = Mapper.toString(game.player);
        con.send(Parse.build(Rule.STA, message));
    }
}
