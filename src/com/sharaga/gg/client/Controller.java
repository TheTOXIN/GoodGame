package com.sharaga.gg.client;

import com.sharaga.gg.utill.Player;
import com.sharaga.gg.utill.State;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

class Controller implements KeyListener, ActionListener
{
	public View v;
	private Service ser;
	private Timer timer;
	private boolean isUp, isLeft, isRight, isDown;
	
	public Controller(Service ser, int delay)
	{
		this.ser = ser;
		timer = new Timer(delay, this);
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
		Player p = Main.players.get(Main.name);
		//TODO add else
		if (isUp)
			p.state = State.UP;
		if (isLeft)
			p.state = State.LEFT;
		if (isRight)
			p.state = State.RIGHT;
		if (isDown)
			p.state = State.DOWN;
			
		ser.informer();
		v.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		Player p = Main.players.get(Main.name);

		switch(e.getKeyCode())
		{
			case 87://w
				isUp = true;
				p.state = State.UP;
				break;
			case 65://a
				isLeft = true;
				p.state = State.LEFT;
				break;
			case 68://d
				isRight = true;
				p.state = State.RIGHT;
				break;
			case 83://s
				isDown = true;
				p.state = State.DOWN;
				break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		Player p = Main.players.get(Main.name);
		switch(e.getKeyCode())
		{
			case 87://w
				isUp = false;
				p.state = State.NONE;
				break;
			case 65://a
				isLeft = false;
				p.state = State.NONE;
				break;
			case 68://d
				isRight = false;
				p.state = State.NONE;
				break;
			case 83://s
				isDown = false;
				p.state = State.NONE;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}
}
