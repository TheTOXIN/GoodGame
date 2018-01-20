import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;

/*Хз как генериксы ваши работают в интерфейсах*/
public class Controller implements EventHandler<KeyEvent>
{
	@Override
	public void handle(KeyEvent e)
	{
		System.out.println("Hello!");
	}
}
