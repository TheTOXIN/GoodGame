package com.sharaga.gg.client;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

	private static String address = "localhost";
	private static int port = 2504;

	private static View view;
	private static Service ser;
	private static Connector con;
	private static Controller control;
	private static Window window;

	private static Game game = new Game();

	public static void main(String args[]) {
		inputDate();

		con = new Connector(port, address);
		ser = new Service(con, game);

		ser.login();

		if (checkConnect()) {
			window = new Window();

			view = new View(game);
			control = new Controller(game.getPlayer(), ser, view);
			control.start();

			window.init(view, control);

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

	public static void setListener(Window window) {
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ser.logout();
				System.exit(0);
			}
		});
	}

}
