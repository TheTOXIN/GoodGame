package com.sharaga.gg.client;

import com.sharaga.gg.utill.Mapper;
import com.sharaga.gg.utill.Parse;
import com.sharaga.gg.utill.Player;
import com.sharaga.gg.utill.Rule;

/**
 * Основной класс для управления пакетами
 */
public class Service {
    private Connector con;

    public Service(Connector con) {
        this.con = con;
    }

    public void start() {
        listener();
    }

    public void login() {
        con.send(Parse.build(Rule.CON, Main.name));
        String data = con.receive();

        Rule answer = Parse.getRule(data);
        con.isConnected = (answer == Rule.TRU);
    }

    public void listener() {
        new Thread(() -> {
            while (con.isConnected) {
                String data = con.receive();

                String message = Parse.getMes(data);
                Rule rule = Parse.getRule(data);

                if (Rule.UPD == rule) {
                    refresh(message);
                }
            }
        }).start();
    }

    public void refresh(String message) {
        Player p = Mapper.toPlayer(message);
        Main.players.put(p.getName(), p);
    }

    public void informer() {
        String message = Mapper.toString(Main.players.get(Main.name));
        con.send(Parse.build(Rule.STA, message));
    }
}
