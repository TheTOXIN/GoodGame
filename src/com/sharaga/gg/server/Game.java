package com.sharaga.gg.server;

import com.sharaga.gg.server.model.Bomb;
import com.sharaga.gg.server.model.Player;
import com.sharaga.gg.server.model.User;
import com.sharaga.gg.server.model.World;
import com.sharaga.gg.server.service.BombService;
import com.sharaga.gg.server.service.UserService;
import com.sharaga.gg.server.service.WoldService;
import com.sharaga.gg.utill.Index;
import com.sharaga.gg.utill.Settings;
import com.sharaga.gg.utill.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {

    public World world;
    public List<Bomb> bombs = new ArrayList<>();
    public boolean isStart;

    public Game(World world) {
        this.isStart = false;
        this.world = world;
    }

    public void start() {
        isStart = true;
        new Thread(() -> {
            System.out.println("...GAME START...");
            loop();
            System.out.println("...GAME END...");
        }).start();
    }

    private void loop() {
        while (isStart) {
            for (Bomb bomb : bombs) {
                int oldX = bomb.getX();
                int oldY = bomb.getY();

                switch (bomb.getState()) {//TODO abstract
                    case UP: bomb.setY(bomb.getY() - 1); break;
                    case LEFT: bomb.setX(bomb.getX() - 1); break;
                    case RIGHT: bomb.setX(bomb.getX() + 1); break;
                    case DOWN: bomb.setY(bomb.getY() + 1); break;
                }

                if (BombService.moving(bomb, world)) {
                    world.getMatrix()[bomb.getY()][bomb.getX()] = Index.BOMB.name();
                    world.getMatrix()[oldY][oldX] = Index.EMPTY.name();
                } else {
                    world.getMatrix()[oldY][oldX] = Index.EMPTY.name();
                    bombs.remove(bomb);
                    break;
                }
            }

            sleep();
        }
    }

    public void move(Player player, State state) {
        int oldX = player.getX();
        int oldY = player.getY();

        switch (state) {
            case UP: player.setY(player.getY() - 1); player.setState(State.UP); break;
            case LEFT: player.setX(player.getX() - 1); player.setState(State.LEFT); break;
            case RIGHT: player.setX(player.getX() + 1); player.setState(State.RIGHT); break;
            case DOWN: player.setY(player.getY() + 1); player.setState(State.DOWN); break;
            case BANG: shoot(player); break;
            case NONE: return;
        }

        boolean valid = WoldService.checking(player.getX(), player.getY());

        if (valid && world.getMatrix()[player.getY()][player.getX()].equals(Index.FOOD.name())) {
            player.setScore(player.getScore() + 1);
            WoldService.spawnFood(world);
            world.getMatrix()[player.getY()][player.getX()] = Index.EMPTY.name();
        }

        if (valid && WoldService.collision(world, player.getX(), player.getY(), Index.EMPTY)) {
            world.getMatrix()[oldY][oldX] = Index.EMPTY.name();
        } else {
            player.setX(oldX);
            player.setY(oldY);
        }

        world.getMatrix()[player.getY()][player.getX()] = player.getName();
    }

    private void shoot(Player player) {
        int y = player.getY();
        int x = player.getX();

        switch (player.getState()) {//TODO abstract
            case UP: y -= 1; break;
            case LEFT: x -= 1; break;
            case RIGHT: x += 1; break;
            case DOWN: y += 1; break;
        }

        Bomb bomb = new Bomb();
        bomb.setX(x);
        bomb.setY(y);
        bomb.setState(player.getState());
        bomb.setColor(player.getColor());
        bomb.setPlayer(player);

        if (!BombService.having(bombs, player.getName()) && BombService.moving(bomb, world)) {
            bombs.add(bomb);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(Settings.SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
