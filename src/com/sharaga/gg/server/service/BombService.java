package com.sharaga.gg.server.service;

import com.sharaga.gg.server.Game;
import com.sharaga.gg.server.model.Bomb;
import com.sharaga.gg.server.model.Player;
import com.sharaga.gg.utill.Index;

import java.util.List;

public class BombService {

    public static boolean having(List<Bomb> bombs, String name) {
        return bombs.stream().anyMatch(bomb -> bomb.getPlayer().equals(name));
    }

    public static boolean moving(Bomb bomb, Game game) {
        return WoldService.checking(bomb.getX(), bomb.getY()) && !destroy(bomb, game) &&
                !(WoldService.collision(game.world, bomb.getX(), bomb.getY(), Index.WALL) ||
                        WoldService.collision(game.world, bomb.getX(), bomb.getY(), Index.FOOD));
    }

    public static boolean destroy(Bomb bomb, Game game) {
        String title = game.world.getMatrix()[bomb.getY()][bomb.getX()];
        Player player = game.players.get(title);
        if (player != null) player.setScore(player.getScore() - 1);

        return player != null;
    }

}
