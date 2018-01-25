package com.sharaga.gg.client;

import com.sharaga.gg.utill.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;

class View extends JComponent implements ActionListener
{
	private Timer timer;
	private int repaintsPerSecond;

	public View()
	{
		repaintsPerSecond = 30;
		timer = new Timer(1000 / repaintsPerSecond, this);
	}
	
	public void start()
	{
		timer.start();
	}
	
	public void stop()
	{
		timer.stop();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		g.setColor(Color.black);
		Player p;
		Point loc;
		int s;

		for (Map.Entry entry : Main.players.entrySet()) {
			p = (Player) entry.getValue();
			loc = p.getLocation();
			s = p.getSize();
			g2D.setColor(p.getColor());
			g2D.fillRect((int)loc.getX(), (int)loc.getY(), s, s);
			g2D.setColor(Color.BLACK);
			g2D.drawString(p.getName(), (int)loc.getX() - 10, (int)loc.getY() - 5);
		}
	}
}
