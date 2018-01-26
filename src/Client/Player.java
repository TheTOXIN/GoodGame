package Client;

import java.awt.Point;
import java.util.Random;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

class Player
{
	private String name;
	private int x;
	private int y;
	private Color color;
	private int size;
	private int step;
	private Rectangle rect;
	
	public Player()
	{
		Random r = new Random();
		this.step = 1;
		color = new Color(r.nextDouble(), r.nextDouble(), r.nextDouble(), 1);
	}
	
	public Player(String name, int x, int y, int size)
	{
		Random r = new Random();
		
		this.name = name;
		this.x = x;
		this.y = y;
		this.size = size;
		
		color = new Color(r.nextDouble() * 255, r.nextDouble() * 255, r.nextDouble() * 255, 1);
		this.step = 1;
	}
	
	public void setName(String name){this.name = name;}
	public String getName(){return name;}
	
	public void setX(int x)
	{
		if(rect != null)
			rect.setX(x);
		this.x = x;
	}
	public double getX(){return x;}

	public void setY(int y)
	{
		if(rect != null)
			rect.setY(y);
		this.y = y;
	}
	public double getY(){return y;}

	public void setColor(Color color)
	{
		this.color = color;
		if(rect != null)
		{
			rect.setFill(color);
		}
	}
	public Color getColor(){return color;}
	public int getRed(){return (int)(color.getRed() * 255);}
	public int getGreen(){return (int)(color.getGreen() * 255);}
	public int getBlue(){return (int)(color.getBlue() * 255);}

	public void setSize(int size)
	{
		this.size = size;
		if(rect != null)
		{
			rect.setWidth(size);
			rect.setHeight(size);
		}
	}
	public int getSize(){return size;}
	
	public int getStep() {return step;}
	public void setStep(int step) {this.step = step;}

	public void setRectangle(Rectangle r)
	{
		this.rect = r;
		rect.setFill(color);
		rect.setWidth(size);
		rect.setHeight(size);
		rect.setX(x);
		rect.setY(y);
	}
	public Rectangle getRectangle(){return this.rect;}

	public void stepUp()
	{
		y -= step;
	}
	
	public void stepLeft()
	{
		x -= step;
	}
	
	public void stepRight()
	{
		x += step;
	}
	
	public void stepDown()
	{
		y += step;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Player player = (Player) o;

		return name != null ? name.equals(player.name) : player.name == null;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}
