package com.sharaga.gg.client;

import com.sharaga.gg.utill.Settings;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main {
	private static final String TITLE = "GOOD GAME";
	private static final int WINDOW_WIDTH = Settings.W * Settings.SIZE + Settings.SIZE / 2;
	private static final int WINDOW_HEIGHT = Settings.H * Settings.SIZE + Settings.SIZE + Settings.SIZE / 2;

	private static String address = "localhost";
	private static int port = 2504;

	private static View view;
	private static Service ser;
	private static Connector con;
	private static Controller control;
	private static Game game = new Game();

	public static void main(String args[]) {
		inputDate();

		con = new Connector(port, address);
		ser = new Service(con, game);

		ser.login();

		if (checkConnect()) {//TODO make Window
			JFrame window = new JFrame(TITLE);
			window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
			window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			view = new View(game);
			control = new Controller(game.getPlayer(), ser, view);
			control.start();

			window.add(view);
			window.addKeyListener(control);
			window.setVisible(true);

			setListener(window);
		}
	}

	private static void inputDate() {
		address = JOptionPane.showInputDialog(null, "IP");
		game.nick = JOptionPane.showInputDialog(null, "NAME");
		if (game.nick.length() > 10) game.nick = game.nick.substring(0, 10);
		if (!address.isEmpty()) address = "localhost";
	}

	private static boolean checkConnect() {
		if (con.isConnected) {
			JOptionPane.showMessageDialog(null, "YOU CONNECT TO SERVER!!!");
			ser.start();
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "YOU CONNECT TO PROBLEM...");
			return false;
		}
	}

	public static void setListener(JFrame frame) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ser.logout();
				System.exit(0);
			}
		});
	}

}
