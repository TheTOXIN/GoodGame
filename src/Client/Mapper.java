package Client;

//import java.awt.*;
import javafx.scene.paint.Color;

public class Mapper {
    public static String toString(Player player) {
        String res = "";

        res += player.getName() + "/";
        res += player.getX() + " " +
                player.getY() + "/";
        res += (int)(player.getColor().getRed() * 255) + " " +
                (int)(player.getColor().getGreen() * 255) + " " +
                (int)(player.getColor().getBlue() * 255) + "/";
        res += player.getSize() + "/";
        res += player.getStep();

        return res;
    }

    public static Player toPlayer(String string) {
        Player res = new Player();
        String[] mapping = string.split("/");

        res.setName(mapping[0]);

        int x = (int)Double.parseDouble(mapping[1].split(" ")[0]);
        int y = (int)Double.parseDouble(mapping[1].split(" ")[1]);
        res.setX(x);
	res.setY(y);

        int r = Integer.parseInt(mapping[2].split(" ")[0]);
        int g = Integer.parseInt(mapping[2].split(" ")[1]);
        int b = Integer.parseInt(mapping[2].split(" ")[2]);
        res.setColor(Color.rgb(r, g, b));

        res.setSize(Integer.parseInt(mapping[3]));
        res.setStep(Integer.parseInt(mapping[4]));

        return res;
    }
}
