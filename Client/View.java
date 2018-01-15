import java.util.ArrayList;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

class View extends JComponent
{
	private ArrayList<Player> players;

	public View()
	{
		players = new ArrayList<Player>();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Player p;
		Point loc;
		int s;
		for(int i = 0; i < players.size(); i++)
		{
			p = players.get(i);
			loc = p.getLocation();
			s = p.getSize();
			g.setColor(p.getColor());
			g.fillRect((int)loc.getX(), (int)loc.getY(), s, s);
		}
	}
}
