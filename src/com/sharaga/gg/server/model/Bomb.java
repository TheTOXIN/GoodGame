package com.sharaga.gg.server.model;

import com.sharaga.gg.utill.State;

import java.awt.*;

public class Bomb {

    public Player player;
    public Color color;
    public State state;
    private int x;
    private int y;

    public Bomb() {

    }

    public Bomb(Player player, Color color, State state, int x, int y) {
        this.player = player;
        this.color = color;
        this.state = state;
        this.x = x;
        this.y = y;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
        if (player != null ? !player.equals(bomb.player) : bomb.player != null) return false;
        if (color != null ? !color.equals(bomb.color) : bomb.color != null) return false;
        return state == bomb.state;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Bomb{" +
                "player=" + player +
                ", color=" + color +
                ", state=" + state +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

}
