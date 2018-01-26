package Client;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.Timer;

public class View extends JComponent {
	private Game game;

	public View(Game game) {
		this.game = game;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		drawPlayer(game.getPlayer(), g2);
		for (Map.Entry entry : game.getPlayers().entrySet()) {
			Player p = (Player) entry.getValue();
			drawPlayer(p, g2);
		}
	}

	private void drawPlayer(Player p, Graphics2D g2) {
		Point loc = p.getLocation();

		g2.setColor(p.getColor());
		g2.fillRect((int)loc.getX(), (int)loc.getY(), p.getSize(), p.getSize());
		System.out.println((int)loc.getX() +  " / " + (int)loc.getY());
		g2.setColor(Color.black);
		g2.drawString(p.getName(), (int)loc.getX(), (int)loc.getY());
	}
}
