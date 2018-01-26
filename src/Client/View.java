package Client;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class View
{
	private Pane root;

	public View()
	{
		root = new Pane();
	}

	public void addRect(Rectangle r)
	{
		root.getChildren().add(r);
	}

	public void render(Stage stage)
	{
		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setWidth(Game.WINDOW_WIDTH);
		stage.setHeight(Game.WINDOW_HEIGHT);
		stage.show();
	}
}
