package com.sharaga.gg.client;

import com.sharaga.gg.utill.Index;
import com.sharaga.gg.utill.Settings;

import javax.swing.JComponent;
import java.awt.*;
import java.util.Random;

public class View extends JComponent {

	private Game game;
	private final Random rand = new Random();

	public View(Game game) {
		this.game = game;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		drawWorld(game.world, g2);
	}

	private void drawWorld(String map, Graphics2D g2) {
		String[] rows = map.split("\n");
		for (int i = 0; i < Settings.H; i++) {
			String[] cols = rows[i].split(" ");
			for (int j = 0; j < Settings.W; j++) {
				if (cols[j].equals(Index.WALL.name())) {
					drawWall(i, j, g2);
				} else if (cols[j].equals(Index.EMPTY.name())) {
					//AHAHHA DONT TOUCH IT (i'm serious...)
				} else if (cols[j].equals(Index.FOOD.name())) {
					drawFood(i, j, g2);
				} else if (cols[j].equals(Index.BOMB.name())) {
					drawBomb(i, j, g2);
				} else {
					drawPlayer(i, j, game.players.get(cols[j]), g2);//TODO NPE
				}
			}
		}
	}

	private void drawBomb(int y, int x, Graphics2D g2) {
		int size = Settings.SIZE / 2;
		g2.setColor(Color.BLACK);
		g2.fillOval(x * Settings.SIZE + size / 2, y * Settings.SIZE + size / 2, size, size);
	}

	private void drawPlayer(int y, int x, Player p, Graphics2D g2) {
		g2.setColor(p.getColor());
		g2.fillRect(x * Settings.SIZE, y * Settings.SIZE, Settings.SIZE, Settings.SIZE);
		if (p.getScore() < 0) g2.setColor(Color.RED); else g2.setColor(Color.BLACK);
		g2.drawString(p.getName() + ":" + p.getScore(), x * Settings.SIZE - 10, y * Settings.SIZE - 5);
	}

	private void drawWall(int y, int x, Graphics2D g2) {
		g2.setColor(Color.black);
		g2.fillRect(x * Settings.SIZE, y * Settings.SIZE, Settings.SIZE, Settings.SIZE);
	}

	private void drawFood(int y, int x, Graphics2D g2) {
		g2.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		g2.fillOval(x * Settings.SIZE, y * Settings.SIZE, Settings.SIZE, Settings.SIZE);
	}

}
