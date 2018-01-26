package com.sharaga.gg.client;

import javax.swing.*;

public class Main {
	private static final String TITLE = "GOOD GAME";
	private static final int WINDOW_WIDTH = 480;
	private static final int WINDOW_HEIGHT = WINDOW_WIDTH;

	private static String address = "localhost";
	private static int port = 2504;

	private static Game game;
	private static View view;
	private static Service ser;
	private static Connector con;
	private static Controller control;

	private static String name;

	public static void main(String args[]) {
		inputDate();

		JFrame window = new JFrame(TITLE);
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		game = new Game(name);
		view = new View(game);

		con = new Connector(port, address);
		ser = new Service(con, game);

		control = new Controller(game.player, ser, view);
		control.start();

		window.add(view);
		window.addKeyListener(control);
		window.setVisible(true);

		ser.login();

		checkConnect();
	}

	private static void inputDate() {
		address = JOptionPane.showInputDialog(null, "IP");
		name = JOptionPane.showInputDialog(null, "NAME");
	}

	private static void checkConnect() {
		if (con.isConnected) {
			JOptionPane.showMessageDialog(null, "YOU CONNECT TO SERVER!!!");
			ser.start();
		} else {
			JOptionPane.showMessageDialog(null, "YOU CONNECT TO PROBLEM...");
		}
	}
}
