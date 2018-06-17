package com.sharaga.gg.server.service;

import com.sharaga.gg.server.model.World;
import com.sharaga.gg.server.model.Player;
import com.sharaga.gg.utill.Index;
import com.sharaga.gg.utill.Settings;
import com.sharaga.gg.utill.State;

import java.util.Random;

public class WoldService {

    private static Random rand = new Random();

    public static World generate(String title) {
        World res = new World();

        String[][]matrix = new String[Settings.H][Settings.W];

        int p = (Settings.H + Settings.W) / 4;
        for (int i = 0; i < Settings.H; i++) {
            for (int j = 0; j < Settings.W; j++) {
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

        for (int i = 0; i < Settings.H; i++) {
            for (int j = 0; j < Settings.W; j++) {
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
            x = rand.nextInt(Settings.W);
            y = rand.nextInt(Settings.H);
        } while (!world.getMatrix()[y][x].equals(Index.EMPTY.name()));

        player.setX(x);
        player.setY(y);
        world.getMatrix()[y][x] = player.getName();
    }

    public static void spawnFood(World world) {
        int x;
        int y;

        do {
            x = rand.nextInt(Settings.W);
            y = rand.nextInt(Settings.H);
        } while (!world.getMatrix()[y][x].equals(Index.EMPTY.name()));

        world.getMatrix()[y][x] = Index.FOOD.name();
    }

    public static void move(World world, Player player, State state) {
        int oldX = player.getX();
        int oldY = player.getY();

        switch (state) {
            case UP: player.setY(player.getY() - 1); player.setState(State.UP); break;
            case LEFT: player.setX(player.getX() - 1); player.setState(State.LEFT); break;
            case RIGHT: player.setX(player.getX() + 1); player.setState(State.RIGHT); break;
            case DOWN: player.setY(player.getY() + 1); player.setState(State.DOWN); break;
            case BANG: shoot(world, player); break;
            case NONE: return;
        }

        if (player.getX() >= Settings.W || player.getX() < 0 ||
            player.getY() >= Settings.H || player.getY() < 0 ||
            !world.getMatrix()[player.getY()][player.getX()].equals(Index.EMPTY.name()) &&
            !world.getMatrix()[player.getY()][player.getX()].equals(Index.FOOD.name()) &&
            !world.getMatrix()[player.getY()][player.getX()].equals(Index.BMOB.name())) {

            player.setX(oldX);
            player.setY(oldY);
        } {
            world.getMatrix()[oldY][oldX] = Index.EMPTY.name();
        }

        if (world.getMatrix()[player.getY()][player.getX()].equals(Index.FOOD.name())) {
            player.setScore(player.getScore() + 1);
            WoldService.spawnFood(world);
        } else if (world.getMatrix()[player.getY()][player.getX()].equals(Index.BMOB.name())) {
            player.setScore(player.getScore() - 1);
        }

        world.getMatrix()[player.getY()][player.getX()] = player.getName();
    }

    private static void shoot(World world, Player player) {
        System.out.println(player.getName() + " : " + player.getState());

        int y = player.getY();
        int x = player.getX();

        switch (player.getState()) {
            case UP: y--; break;
            case LEFT: x--; break;
            case RIGHT: x++; break;
            case DOWN: y++; break;
        }

        world.getMatrix()[y][x] = Index.BMOB.name();
    }

    public static void remove(World world, Player player) {
        world.getMatrix()[player.getY()][player.getX()] = Index.EMPTY.name();
    }

}
