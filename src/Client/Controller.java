package Client;

import Utill.State;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

class Controller implements KeyListener, ActionListener
{
	public View v;
	private Service ser;
	private Player player;
	private Timer timer;
	private boolean isUp, isLeft, isRight, isDown;
	
	public Controller(Player player, Service ser, int delay)
	{
		this.player = player;
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
		for (Player p : Main.players) {
			if (p.state == State.UP)
				p.stepUp();
			if (p.state == State.LEFT)
				p.stepLeft();
			if (p.state == State.RIGHT)
				p.stepRight();
			if (p.state == State.DOWN)
				p.stepDown();
		}

		v.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		State prev = player.state;
		switch(e.getKeyCode())
		{
			case 87://w
				//isUp = true;
				player.state = State.UP;
				break;
			case 65://a
				//isLeft = true;
				player.state = State.LEFT;
				break;
			case 68://d
				//isRight = true;
				player.state = State.RIGHT;
				break;
			case 83://s
				//isDown = true;
				player.state = State.DOWN;
				break;
		}
		if (prev != player.state)
			ser.informer();
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		State prev = player.state;
		switch(e.getKeyCode())
		{
			case 87://w
				//isUp = false;
				player.state = State.NONE;
				break;
			case 65://a
				//isLeft = false;
				player.state = State.NONE;
				break;
			case 68://d
				//isRight = false;
				player.state = State.NONE;
				break;
			case 83://s
				//isDown = false;
				player.state = State.NONE;
				break;
		}
		if (prev != player.state)
			ser.informer();
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
	}
}
