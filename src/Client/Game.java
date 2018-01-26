package Client;

import javafx.application.Application;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.awt.Color;

public class Game extends Application
{
	public static String address;
	public static int port = 2504;
	public static View view;
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static Player mainPlayer;
	private HashMap<KeyCode, Boolean> keys;
	public static final String TITLE = "GOOD GAME";
	public static final int WINDOW_WIDTH = 480;
	public static final int WINDOW_HEIGHT = WINDOW_WIDTH;
	private AnimationTimer timer;
	private Connector con;

	public Game()
	{
		Rectangle r = new Rectangle(20, 20, javafx.scene.paint.Color.BLACK);

		mainPlayer = new Player();
		mainPlayer.setName("p" + new Random().nextInt(100));
		mainPlayer.setSize(25);
		mainPlayer.setRectangle(r);
		players.add(mainPlayer);

		view = new View();
		view.addRect(r);

		keys = new HashMap<KeyCode, Boolean>();
		keys.put(KeyCode.UP, false);
		keys.put(KeyCode.LEFT, false);
		keys.put(KeyCode.RIGHT, false);
		keys.put(KeyCode.DOWN, false);
		timer = new AnimationTimer() {
            		@Override
            		public void handle(long now)
            		{
				if(keys.getOrDefault(KeyCode.UP, false))
					mainPlayer.setY((int)mainPlayer.getY() - mainPlayer.getStep());
				if(keys.getOrDefault(KeyCode.LEFT, false))
					mainPlayer.setX((int)mainPlayer.getX() - mainPlayer.getStep());
				if(keys.getOrDefault(KeyCode.RIGHT, false))
					mainPlayer.setX((int)mainPlayer.getX() + mainPlayer.getStep());
				if(keys.getOrDefault(KeyCode.DOWN, false))
					mainPlayer.setY((int)mainPlayer.getY() + mainPlayer.getStep());
			}
        	};
	}

	@Override
	public void start(Stage stage)
	{
		view.render(stage);
		Scene scene = stage.getScene();
		scene.setOnKeyPressed(event->
		{
			if(keys.get(event.getCode()) != null)
				keys.put(event.getCode(), true);
		});
		scene.setOnKeyReleased(event->
		{
			if(keys.get(event.getCode()) != null)
				keys.put(event.getCode(), false);
		});
		if(address == null)
		{
			System.out.print("IP ->");
			Scanner sc = new Scanner(System.in);
			address = sc.nextLine();
		}
		con = new Connector(port, address);

		Service service = new Service(con, mainPlayer);
		service.login();
		if(con.isConnected)
		{
			System.out.println("Connect to server!");
			service.start();
		}
		else
			System.out.println("Connect to problem...");
		timer.start();
	}
}
