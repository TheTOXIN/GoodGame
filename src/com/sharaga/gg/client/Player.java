package com.sharaga.gg.client;

import com.sharaga.gg.utill.State;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Player {
	private String name;
	private Color color;
	private State state;
	private int score;
	private int step;
	
	public Player() {
		this.step = 1;
	}

	public void setName(String name){this.name = name;}
	public String getName(){return name;}

	public void setColor(Color color){this.color = color;}
	public Color getColor(){return color;}
	
	public void setScore(int score){this.score = score;}
	public int getScore(){return score;}
	
	public int getStep() {return step;}
	public void setStep(int step) {this.step = step;}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Player player = (Player) o;

		if (score != player.score) return false;
		if (step != player.step) return false;
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
		result = 31 * result + step;
		return result;
	}

	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				", color=" + color +
				", state=" + state +
				", score=" + score +
				", step=" + step +
				'}';
	}

}
