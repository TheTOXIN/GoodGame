package com.sharaga.gg.client;


import com.sharaga.gg.utill.Const;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

	private static String address = "localhost";

	private static Connector con;
	private static Service ser;

	private static Controller control;
	private static Window window;
	private static View view;

	private static Room room = new Room();

	public static void main(String args[]) throws Exception {
		inputDate();

		con = new Connector(Const.PORT, address);
		ser = new Service(con, room);

		ser.login();

		if (checkConnect()) {
			ser.start();

			view = new View(room);
			window = new Window();
			control = new Controller(ser, view);

			window.init(view, control);
			setListener(window);
		}
	}

	private static void inputDate() {
		address = JOptionPane.showInputDialog(null, "IP");
		room.nick = JOptionPane.showInputDialog(null, "NAME");

		if (room.nick.length() > 10) room.nick = room.nick.substring(0, 10);
		if (room.nick.isEmpty()) room.nick = "DEBIL";
		if (address.isEmpty()) address = "localhost";
	}

	private static boolean checkConnect() {
		if (con.isConnected) {
			JOptionPane.showMessageDialog(null, "YOU CONNECT TO SERVER!!!");
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
