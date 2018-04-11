package com.sharaga.gg.client;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.awt.Rectangle;

public class Player {
	private String name;
	private Point location;
	private Color color;
	private int size;
	private int step;
	
	public Player() {
		this.step = 1;
	}
	
	public Player(String name, Point location, int size) {
		Random r = new Random();
		
		this.name = name;
		this.location = location;
		this.size = size;
		
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		this.step = 1;
	}
	
	public void setName(String name){this.name = name;}
	public String getName(){return name;}
	
	public void setLocation(Point location){this.location = location;}
	public void setLocation(int x, int y){this.location = new Point(x, y);}
	public Point getLocation(){return location;}
	
	public void setColor(Color color){this.color = color;}
	public Color getColor(){return color;}
	
	public void setSize(int size){this.size = size;}
	public int getSize(){return size;}
	
	public int getStep() {return step;}
	public void setStep(int step) {this.step = step;}

	public Rectangle getRectangle() {
		return new Rectangle((int)location.getX(), (int)location.getY(), size, size);
	}

	public void stepUp() {
		location.translate(0, -step);
	}
	
	public void stepLeft() {
		location.translate(-step, 0);
	}
	
	public void stepRight() {
		location.translate(step, 0);
	}
	
	public void stepDown() {
		location.translate(0, step);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Player player = (Player) o;

		return name != null ? name.equals(player.name) : player.name == null;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}