package com.sharaga.gg.client;

import com.sharaga.gg.utill.Player;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main {
	public static String address = "localhost";
	public static int port = 2504;
	public static String name = "USER";

	public static Map<String, Player> players = new HashMap<>();

	private static final String TITLE = "GOOD GAME";
	private static final int WINDOW_WIDTH = 480;
	private static final int WINDOW_HEIGHT = WINDOW_WIDTH;

	public static void main(String args[]) throws InterruptedException {
		JFrame window = new JFrame(TITLE);
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Scanner sc = new Scanner(System.in);

		System.out.print("IP => ");
		address = sc.nextLine();
		System.out.print("NAME => ");
		name = sc.nextLine();

		Player player = new Player(name, new Point(0,0), 32);
		players.put(player.getName(), player);

		View view = new View();
		window.add(view);

		Connector con = new Connector(port, address);
		Service ser = new Service(con);

		Controller controller = new Controller(ser, 1000/60);
		controller.v = view;

		window.addKeyListener(controller);
		window.setVisible(true);

		controller.start();

		ser.login();
		if (con.isConnected) {
			System.out.println("Connect to server!");
			ser.start();
		} else {
			System.out.println("Connect to problem...");
		}
	}
}
