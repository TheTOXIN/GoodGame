package com.sharaga.gg.server.model;

import com.sharaga.gg.utill.State;

import java.awt.*;

public class Bomb extends Objector {

    public Player player;

    public Bomb() {

    }

    public Bomb(Player player, Color color, State state, int x, int y) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bomb bomb = (Bomb) o;

        return player != null ? player.equals(bomb.player) : bomb.player == null;
    }

    @Override
    public int hashCode() {
        return player != null ? player.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Bomb{" +
            "player=" + player +
            '}';
    }

}
