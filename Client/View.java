import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

class View extends JComponent implements ActionListener
{
	private Timer timer;
	private int repaintsPerSecond;

	public View()
	{
		repaintsPerSecond = 30;
		timer = new Timer(1000 / repaintsPerSecond, this);
	}
	
	public void start()
	{
		timer.start();
	}
	
	public void stop()
	{
		timer.stop();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		Player p;
		Point loc;
		int s;
		for(int i = 0; i < Main.players.size(); i++)
		{
			p = Main.players.get(i);
			loc = p.getLocation();
			s = p.getSize();
			g.setColor(p.getColor());
			g.fillRect((int)loc.getX(), (int)loc.getY(), s, s);
		}
	}
}
