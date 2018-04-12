package com.sharaga.gg.client;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Controller implements KeyListener, ActionListener {
	private Player player;
	private Timer timer;
	private Service ser;
	private View view;

	private boolean isUp, isLeft, isRight, isDown;
	
	public Controller(Player player, Service ser, View view) {
		this.ser = ser;
		this.view = view;
		this.player = player;
		this.timer = new Timer(100/60, this);
	}
	
	public void start() {
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isUp)
			player.stepUp();
		if(isLeft)
			player.stepLeft();
		if(isRight)
			player.stepRight();
		if(isDown)
			player.stepDown();

		ser.informer();
		view.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case 87://w
				isUp = true;
				break;
			case 65://a
				isLeft = true;
				break;
			case 68://d
				isRight = true;
				break;
			case 83://s
				isDown = true;
				break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case 87://w
				isUp = false;
				break;
			case 65://a
				isLeft = false;
				break;
			case 68://d
				isRight = false;
				break;
			case 83://s
				isDown = false;
				break;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
