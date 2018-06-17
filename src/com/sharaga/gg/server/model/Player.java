package com.sharaga.gg.server.model;

import com.sharaga.gg.utill.State;

import java.awt.*;

public class Player {

    private String name;
    private Color color;
    private State state;
    private int score;
    private int x;
    private int y;

    public Player() {
    }

    public Player(String name, Color color, State state, int score, int x, int y) {
        this.name = name;
        this.color = color;
        this.state = state;
        this.score = score;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

        Player player = (Player) o;

        if (score != player.score) return false;
        if (x != player.x) return false;
        if (y != player.y) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (color != null ? !color.equals(player.color) : player.color != null) return false;
        return state == player.state;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", state=" + state +
                ", score=" + score +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

}
