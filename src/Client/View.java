<<<<<<< HEAD:Client/View.java
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
=======
package Client;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
>>>>>>> ae256ee7de16ae6fe981ebcef40d278f78d88496:src/Client/View.java

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class View extends Scene
{
	//private ArrayList<Player> players;
	private Rectangle mainPlayer;
	private Group root;
	//private HashMap<KeyCode, Boolean> keys;

	public View(Group root)
	{
		super(root);
		mainPlayer = new Rectangle();
		this.root.getChildren().add(mainPlayer);
	
		
	}

	//@Override
	/*public void start(Stage stage) throws Exception
	{
		int step = 2;
		long delay = 1000 / 60;
		Random r = new Random();
		Color color = new Color(r.nextDouble(), r.nextDouble(), r.nextDouble(), 1);
		
		keys = new HashMap<>();
		keys.put(KeyCode.UP, false);
		keys.put(KeyCode.LEFT, false);
		keys.put(KeyCode.RIGHT, false);
		keys.put(KeyCode.DOWN, false);
		
		mainPlayer = new Rectangle(20, 20, color);
		
		root = new Group();
		root.getChildren().add(mainPlayer);
		
		Scene scene = new Scene(root);
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
		
		AnimationTimer timer = new AnimationTimer() {
            		@Override
            		public void handle(long now)
            		{
				if(keys.getOrDefault(KeyCode.UP, false))
					mainPlayer.setY(mainPlayer.getY() - step);
				if(keys.getOrDefault(KeyCode.LEFT, false))
					mainPlayer.setX(mainPlayer.getX() - step);
				if(keys.getOrDefault(KeyCode.RIGHT, false))
					mainPlayer.setX(mainPlayer.getX() + step);
				if(keys.getOrDefault(KeyCode.DOWN, false))
					mainPlayer.setY(mainPlayer.getY() + step);
			}
        	};
        	timer.start();
		
		
	}*/
}