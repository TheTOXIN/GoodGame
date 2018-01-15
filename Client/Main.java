import javax.swing.JFrame;

import java.awt.Color;

class Main
{
	static final String TITLE = "GOOD GAME";
	static final int WINDOW_WIDTH = 480;
	static final int WINDOW_HEIGHT = WINDOW_WIDTH; 

	public static void main(String args[])
	{
		JFrame window = new JFrame(TITLE);
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		View view = new View();
		window.add(view);
		
		window.setVisible(true);
		
		Player p1 = new Player();
		p1.setName("p1");
		p1.setLocation(0, 0);
		p1.setSize(25);
		p1.setColor(Color.green);
		view.players.add(p1);
	}
}
