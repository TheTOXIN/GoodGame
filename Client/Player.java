import java.awt.Color;
import java.awt.Point;
import java.util.Random;

class Player
{
	private String name;
	private Point location;
	private Color color;
	private int size;
	private int step;
	
	public Player()
	{
		this.step = 20;
	}
	
	public Player(String name, Point location, int size)
	{
		Random r = new Random();
		
		this.name = name;
		this.location = location;
		this.size = size;
		
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		this.step = 20;
	}
	
	public void setName(String name){this.name = name;}
	public String getName(){return name;}
	
	public void setLocation(Point location){this.location = location;}
	public void setLocation(int x, int y){this.location = new Point(x, y);}
	public Point getLocation(){return location;}
	
	public void setColor(Color color){this.color = color;}
	public Color getColor(){return color;}
	
	public void setSize(int size){this.size = size;}
	public int getSize(){return size;}
	
	public int getStep() {return step;}
	public void setStep(int step) {this.step = step;}

	public void stepUp()
	{
		location.move(0, -step);
	}
	
	public void stepLeft()
	{
		location.move(-step, 0);
	}
	
	public void stepRight()
	{
		location.move(step, 0);
	}
	
	public void stepDown()
	{
		location.move(0, step);
	}
}
