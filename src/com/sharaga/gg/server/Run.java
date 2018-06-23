package com.sharaga.gg.server;

import com.sharaga.gg.server.model.World;
import com.sharaga.gg.server.service.WoldService;

public class Run {
    public static void main(String[] args) {
        System.out.println("-=RUN=-");

        World world = WoldService.generate("HELLO WORLD");
        Game game = new Game(world);

        Server server = Server.setInstance(game, 2504);
        server.start();
    }
}
