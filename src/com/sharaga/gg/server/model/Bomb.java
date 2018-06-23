package com.sharaga.gg.server.model;

import com.sharaga.gg.utill.State;

public class Bomb {

    public State state;
    public String player;
    private int x;
    private int y;

    public Bomb() {

    }

    public Bomb(State state, String player, int x, int y) {
        this.state = state;
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bomb bomb = (Bomb) o;

        if (x != bomb.x) return false;
        if (y != bomb.y) return false;
        if (state != bomb.state) return false;
        return player != null ? player.equals(bomb.player) : bomb.player == null;
    }

    @Override
    public int hashCode() {
        int result = state != null ? state.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Bomb{" +
                "state=" + state +
                ", player='" + player + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

}
