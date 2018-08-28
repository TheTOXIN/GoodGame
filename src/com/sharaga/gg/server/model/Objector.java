package com.sharaga.gg.server.model;

import com.sharaga.gg.utill.State;

import java.awt.*;

public abstract class Objector {

    private Color color;
    private State state;
    private int x;
    private int y;

    public Objector() {
    }

    public Objector(Color color, State state, int x, int y) {
        this.color = color;
        this.state = state;
        this.x = x;
        this.y = y;
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

        Objector objector = (Objector) o;

        if (x != objector.x) return false;
        if (y != objector.y) return false;
        if (color != null ? !color.equals(objector.color) : objector.color != null) return false;
        return state == objector.state;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Objector{" +
            "color=" + color +
            ", state=" + state +
            ", x=" + x +
            ", y=" + y +
            '}';
    }

}
