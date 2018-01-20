<<<<<<< HEAD:Client/Controller.java
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
=======
package Client;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
>>>>>>> ae256ee7de16ae6fe981ebcef40d278f78d88496:src/Client/Controller.java

/*Хз как генериксы ваши работают в интерфейсах*/
public class Controller implements EventHandler<KeyEvent>
{
	@Override
	public void handle(KeyEvent e)
	{
		System.out.println("Hello!");
	}
}
