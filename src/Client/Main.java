package Client;

import javax.swing.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	public static String address = "localhost";
	public static int port = 2504;

	public static List<Player> players = new ArrayList<>();

	private static final String TITLE = "GOOD GAME";
	private static final int WINDOW_WIDTH = 480;
	private static final int WINDOW_HEIGHT = WINDOW_WIDTH;

	public static void main(String args[]) throws InterruptedException {
		JFrame window = new JFrame(TITLE);
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Player p = new Player();
		p.setName("p" + new Random().nextInt(100));
		p.setLocation(0, 0);
		p.setSize(25);
		p.setColor(Color.black);

		View view = new View();
		players.add(p);
		window.add(view);

		Controller controller = new Controller(p, 1000/60);
		controller.v = view;

		window.addKeyListener(controller);
		window.setVisible(true);

		controller.start();

		Connector con = new Connector(port, address);
		Service ser = new Service(con, p);

		ser.login();
		if (con.isConnected) {
			System.out.println("Connect to server!");
			ser.start();
		} else {
			System.out.println("Connect to problem...");
		}
	}
}