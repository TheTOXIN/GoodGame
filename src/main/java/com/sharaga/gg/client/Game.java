package com.sharaga.gg.client;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Game {
    public Player player;
    public Map<String, Player> players = new HashMap<>();

    public Game(String nick) {
        player = new Player(nick, new Point(0, 0), 32);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, Player> players) {
        this.players = players;
    }
}
