package com.sharaga.gg.server;

import com.sharaga.gg.server.model.Bomb;
import com.sharaga.gg.server.model.Player;
import com.sharaga.gg.server.model.World;
import com.sharaga.gg.server.service.BombService;
import com.sharaga.gg.server.service.WoldService;
import com.sharaga.gg.utill.Const;
import com.sharaga.gg.utill.Index;
import com.sharaga.gg.utill.State;

import java.util.ArrayList;
import java.util.List;

import static com.sharaga.gg.utill.Const.*;
import static java.lang.System.nanoTime;

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
        boolean updater = false;

        long ticks = 0;
        long tickTime = nanoTime();

        long currTime;
        long deltaTime;

        long unprocessedTime = 0;
        long prevTime = nanoTime();

        while (isStart) {
            currTime = nanoTime();

            deltaTime = currTime - prevTime;
            prevTime = currTime;

            unprocessedTime += deltaTime;

            while (unprocessedTime >= FPS_PER_UPD) {
                unprocessedTime -= FPS_PER_UPD;
                prevTime = nanoTime();
                updater = true;
            }

            if (updater) {
                updater = false;
                update();
                ticks++;
            }

            if (nanoTime() - tickTime >= MAGIC) {
                System.out.println("TICK FPS: " + ticks);
                tickTime += MAGIC;
                ticks = 0;
            }
        }
    }

    public void update() {
        for (Bomb bomb : bombs) {
            int oldX = bomb.getX();
            int oldY = bomb.getY();

            switch (bomb.getState()) {
                case UP -> bomb.setY(bomb.getY() - 1);
                case LEFT -> bomb.setX(bomb.getX() - 1);
                case RIGHT -> bomb.setX(bomb.getX() + 1);
                case DOWN -> bomb.setY(bomb.getY() + 1);
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
    }

    public void move(Player player, State state) {
        String[][] matrix = world.getMatrix();

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

        if (valid && matrix[player.getY()][player.getX()].equals(Index.FOOD.name())) {
            player.setScore(player.getScore() + 1);
            WoldService.spawnFood(world);
            matrix[player.getY()][player.getX()] = Index.EMPTY.name();
        }

        if (valid && WoldService.collision(world, player.getX(), player.getY(), Index.EMPTY)) {
            matrix[oldY][oldX] = Index.EMPTY.name();
        } else {
            player.setX(oldX);
            player.setY(oldY);
        }

        matrix[player.getY()][player.getX()] = player.getName();
    }

    private void shoot(Player player) {
        int y = player.getY();
        int x = player.getX();

        switch (player.getState()) {
            case UP -> y -= 1;
            case LEFT -> x -= 1;
            case RIGHT -> x += 1;
            case DOWN -> y += 1;
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
}
