package com.sharaga.gg.client;

import com.sharaga.gg.utill.State;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Objects;

public class Player {

	private String name;
	private Color color;
	private int score;
	
	public Player() {

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
		return score == player.score &&
			Objects.equals(name, player.name) &&
			Objects.equals(color, player.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, color, score);
	}

	@Override
	public String toString() {
		return "Player{" +
			"name='" + name + '\'' +
			", score=" + score +
			'}';
	}
}

