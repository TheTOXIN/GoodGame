package com.sharaga.gg.server.service;

import com.sharaga.gg.server.Game;
import com.sharaga.gg.server.model.Player;
import com.sharaga.gg.utill.State;

import java.awt.*;
import java.util.Random;

public class PlayerService {

    private static final int DEF_SIZE = 1;

    private static Random rand = new Random();

    public static Player create(String name, Game game) {
        Player res = new Player();

        res.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
        res.setScore(DEF_SIZE);
        res.setName(name);
        res.setState(State.NONE);

        WoldService.spawn(res, game.world);

        return res;
    }

    public static String string(Player player) {
        String res = "";

        res += "name:" + player.getName() + "\n";
        res += "color:" + player.getColor().getRGB() + "\n";
        res += "state:" + player.getState().name() + "\n";
        res += "score:" + player.getScore() + "\n";
        res += "x:" + player.getX() + "\n";
        res += "y:" + player.getY() + "\n";

        return res;
    }

}
