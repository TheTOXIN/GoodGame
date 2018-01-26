package Client;

import Utill.Parse;
import Utill.Rule;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.application.Platform;
/**
 * Основной класс для управления пакетами
 */
public class Service {
    private Connector con;
    private Player player;

    public Service(Connector con, Player player) {
        this.con = con;
        this.player = player;
    }

    public void login() {
        con.send(Parse.build(Rule.CON, player.getName()));
        Rule answer = Parse.getRule(con.receive());
        con.isConnected = answer == Rule.TRU;
    }

    public void start() {
        listener();
        informer();
    }

    public void listener() {
	    new Thread(() -> {
            while (con.isConnected) {
                String data = con.receive();
                String message = Parse.getMes(data);
                Rule rule = Parse.getRule(data);

                if (Rule.MES == rule)
		{
                    Player p = Mapper.toPlayer(message);
		    int ind = Game.players.indexOf(p);
		    if(ind == -1)
		    {
			    Rectangle r = new Rectangle((double)p.getX(), (double)p.getY(), (double)p.getSize(), (double)p.getSize());
			    r.setFill(Color.rgb(p.getRed(), p.getGreen(), p.getBlue()));
			    p.setRectangle(r);
			    Platform.runLater(()-> Game.view.addRect(r));
			    //Game.view.addRect(r);
			    Game.players.add(p);
		    }
		    else if(ind > 0)
		    {
			    Player curP = Game.players.get(ind);
			    curP.setX((int)p.getX());
			    curP.setY((int)p.getY());
			    curP.setColor(p.getColor());
			    curP.setSize(p.getSize());
			    curP.setStep(p.getStep());
		    }
                    /*if (ind != -1)
		    {
			    Player curP = Game.players.get(ind);
			    curP.setX((int)p.getX());
			    curP.setY((int)p.getY());
			    curP.setColor(p.getColor());
			    curP.setSize(p.getSize());
			    curP.setStep(p.getStep());
                    }
		    else
		    {
			    Rectangle r = new Rectangle((double)p.getX(), (double)p.getY(), (double)p.getSize(), (double)p.getSize());
			    r.setFill(Color.color(p.getRed(), p.getGreen(), p.getBlue()));
			    p.setRectangle(r);
			    Game.players.add(p);
		    }*/
                }
            }
        }).start();
    }

    public void informer() {
        new Thread(() -> {
            while (con.isConnected) {
                try {
                    Thread.sleep(10);
                    String message = Mapper.toString(player);
                    con.send(Parse.build(Rule.MES, message));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
