package com.sharaga.gg.client;

import java.awt.*;

public class Mapper {
    public static String toString(Player player) {
        String res = "";

        res += player.getName() + "/";
        res += player.getLocation().getX() + " " +
                player.getLocation().getY() + "/";
        res += player.getColor().getRed() + " " +
                player.getColor().getGreen() + " " +
                player.getColor().getBlue() + "/";
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
        res.setLocation(new Point(x, y));

        int r = Integer.parseInt(mapping[2].split(" ")[0]);
        int g = Integer.parseInt(mapping[2].split(" ")[1]);
        int b = Integer.parseInt(mapping[2].split(" ")[2]);
        res.setColor(new Color(r, g, b));

        res.setSize(Integer.parseInt(mapping[3]));
        res.setStep(Integer.parseInt(mapping[4]));

        return res;
    }
}
