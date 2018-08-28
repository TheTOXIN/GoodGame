package com.sharaga.gg.client;

import com.sharaga.gg.utill.State;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Player {
	private String name;
	private Color color;
	private State state;
	private State prevState;

	private int score;
	
	public Player() {
		this.prevState = State.NONE;
		this.state = State.NONE;
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

	public State getPrevState() {
		return prevState;
	}

	public void setPrevState(State nextState) {
		this.prevState = nextState;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Player player = (Player) o;

		if (score != player.score) return false;
		if (name != null ? !name.equals(player.name) : player.name != null) return false;
		if (color != null ? !color.equals(player.color) : player.color != null) return false;
		if (state != player.state) return false;
		return prevState == player.prevState;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (color != null ? color.hashCode() : 0);
		result = 31 * result + (state != null ? state.hashCode() : 0);
		result = 31 * result + (prevState != null ? prevState.hashCode() : 0);
		result = 31 * result + score;
		return result;
	}

	@Override
	public String toString() {
		return "Player{" +
			"name='" + name + '\'' +
			", color=" + color +
			", state=" + state +
			", prevState=" + prevState +
			", score=" + score +
			'}';
	}

}

