package com.sharaga.gg.client;

import java.util.HashMap;
import java.util.Map;

public class Room {

    public String nick;
    public String world;
    public Map<String, Player> players = new HashMap<>();
    public boolean isSleep = false;

    public Player getPlayer() {
        return players.get(nick);
    }
}
