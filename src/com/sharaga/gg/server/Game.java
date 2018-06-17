package com.sharaga.gg.server;

import com.sharaga.gg.server.model.Bomb;
import com.sharaga.gg.server.model.World;
import com.sharaga.gg.server.service.WoldService;

import java.util.ArrayList;
import java.util.List;

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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
