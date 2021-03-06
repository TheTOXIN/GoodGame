package com.sharaga.gg.server.service;

import com.sharaga.gg.server.model.World;
import com.sharaga.gg.server.model.Player;
import com.sharaga.gg.utill.Index;
import com.sharaga.gg.utill.Const;

import java.util.Random;

public class WoldService {

    private static Random rand = new Random();

    public static World generate(String title) {
        World res = new World();

        String[][]matrix = new String[Const.H][Const.W];

        int p = (Const.H + Const.W) / 4;
        for (int i = 0; i < Const.H; i++) {
            for (int j = 0; j < Const.W; j++) {
                if (rand.nextInt(p) == 0) {
                    matrix[i][j] = Index.WALL.name();
                } else {
                    matrix[i][j] = Index.EMPTY.name();
                }
            }
        }

        res.setTitle(title);
        res.setMatrix(matrix);

        spawnFood(res);

        return res;
    }

    public static String string(World world) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < Const.H; i++) {
            for (int j = 0; j < Const.W; j++) {
               res.append(world.getMatrix()[i][j]).append(" ");
            }
            res.append("\n");
        }

        return res.toString();
    }

    public static void spawnPlayer(Player player, World world) {
        int x;
        int y;

        do {
            x = rand.nextInt(Const.W);
            y = rand.nextInt(Const.H);
        } while (!world.getMatrix()[y][x].equals(Index.EMPTY.name()));

        player.setX(x);
        player.setY(y);
        world.getMatrix()[y][x] = player.getName();
    }

    public static void spawnFood(World world) {
        int x;
        int y;

        do {
            x = rand.nextInt(Const.W);
            y = rand.nextInt(Const.H);
        } while (!world.getMatrix()[y][x].equals(Index.EMPTY.name()));

        world.getMatrix()[y][x] = Index.FOOD.name();
    }

    public static void remove(World world, Player player) {
        world.getMatrix()[player.getY()][player.getX()] = Index.EMPTY.name();
    }

    public static boolean checking(int x, int y) {
        return !(x >= Const.W || x < 0 || y >= Const.H || y < 0);
    }

    public static boolean collision(World world, int x, int y, Index index) {
        return world.getMatrix()[y][x].equals(index.name());
    }
}
