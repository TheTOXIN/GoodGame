package com.sharaga.gg.server.service;

import com.sharaga.gg.server.Server;
import com.sharaga.gg.server.model.Bomb;
import com.sharaga.gg.server.model.Player;
import com.sharaga.gg.server.model.User;
import com.sharaga.gg.server.model.World;
import com.sharaga.gg.utill.Index;

import java.util.List;

public class BombService {

    public static boolean having(List<Bomb> bombs, String name) {
        for (Bomb bomb : bombs) {
            if (bomb.getPlayer().getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public static boolean moving(Bomb bomb, World world) {
        return WoldService.checking(bomb.getX(), bomb.getY()) && !destroy(bomb) &&
                !(WoldService.collision(world, bomb.getX(), bomb.getY(), Index.WALL) ||
                        WoldService.collision(world, bomb.getX(), bomb.getY(), Index.FOOD));
    }

    public static boolean destroy(Bomb bomb) {
        for (User user : Server.users) {
            Player player = user.getPlayer();
            if (player.getX() == bomb.getX() && player.getY() == bomb.getY()) {
                player.setScore(player.getScore() - 1);
                return true;
            }
        }
        return false;
    }

}
