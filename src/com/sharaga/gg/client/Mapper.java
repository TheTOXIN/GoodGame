package com.sharaga.gg.client;

import java.awt.*;


public class Mapper {

    public static Player toPlayer(String string) {
        Player res = new Player();

        res.setName(toFind("name", string));
        res.setColor(Color.decode(toFind("color", string)));
        res.setScore(Integer.parseInt(toFind("score", string)));

        return res;
    }

    private static String toFind(String param, String string) {
        String[] fields = string.split("\n");
        for (String str : fields) {
            if (str.substring(0, str.indexOf(":")).equals(param)) {
                return str.substring(str.indexOf(":") + 1, str.length());
            }
        }

        return "";
    }

}
