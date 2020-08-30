package com.sharaga.gg.client;

import com.sharaga.gg.utill.Index;
import com.sharaga.gg.utill.Const;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static com.sharaga.gg.utill.Const.*;

public class View extends JPanel {

	private Room room;
	private final Random rand = new Random();

	public View(Room room) {
		this.room = room;
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHints(rh);

		drawBack(g2);
		drawWorld(room.world, g2);
	}

	private void drawBack(Graphics2D g2) {
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(0, 0, SIZE * W, SIZE * H);
	}

	private void drawWorld(String map, Graphics2D g2) {
		String[] rows = map.split("\n");
		for (int i = 0; i < H; i++) {
			String[] cols = rows[i].split(" ");
			for (int j = 0; j < W; j++) {
				if (cols[j].equals(Index.WALL.name())) {
					drawWall(i, j, g2);
				} else if (cols[j].equals(Index.EMPTY.name())) {
					//AHAHHA DONT TOUCH THIS (i'm serious...)
				} else if (cols[j].equals(Index.FOOD.name())) {
					drawFood(i, j, g2);
				} else if (cols[j].equals(Index.BOMB.name())) {
					drawBomb(i, j, g2);
				} else {
					drawPlayer(i, j, room.players.get(cols[j]), g2);
				}
			}
		}
	}

	private void drawBomb(int y, int x, Graphics2D g2) {
		int size = SIZE / 2;

		g2.setColor(Color.BLACK);
		g2.fillOval(x * SIZE + size / 2, y * SIZE + size / 2, size, size);
	}

	private void drawPlayer(int y, int x, Player p, Graphics2D g2) {
		g2.setColor(p.getColor());
		g2.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);

		if (p.getScore() < 0) g2.setColor(Color.RED);else g2.setColor(Color.BLACK);
		g2.drawString(p.getName() + ":" + p.getScore(), x * SIZE - 10, y * SIZE - 5);
	}

	private void drawWall(int y, int x, Graphics2D g2) {
		g2.setColor(Color.black);
		g2.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
	}

	private void drawFood(int y, int x, Graphics2D g2) {
		g2.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		g2.fillOval(x * SIZE, y * SIZE, SIZE, SIZE);
	}
}
