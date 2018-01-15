import java.awt.Color;
import java.awt.Point;

class Player
{
	private String name;
	private Point location;
	private Color color;
	private int size;
	
	public Player()
	{
		
	}
	
	public void setName(String name){this.name = name;}
	public String getName(){return name;}
	
	public void setLocation(Point location){this.location = location;}
	public Point getLocation(){return location;}
	
	public void setColor(Color color){this.color = color;}
	public Color getColor(){return color;}
	
	public void setSize(int size){this.size = size;}
	public int getSize(){return size;}
}
