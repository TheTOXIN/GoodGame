package com.sharaga.gg.server;

import com.sharaga.gg.server.model.Player;
import com.sharaga.gg.server.model.User;
import com.sharaga.gg.server.service.PlayerService;
import com.sharaga.gg.server.service.UserService;
import com.sharaga.gg.server.service.WoldService;
import com.sharaga.gg.utill.Parse;
import com.sharaga.gg.utill.Rule;
import com.sharaga.gg.utill.State;

import java.net.DatagramPacket;
import java.util.Map;

public class Eventer {

    private static Server server = Server.getInstance();

    public void newConnection(DatagramPacket packet) {
        String name = Parse.getMes(packet);
        User user = new User(packet, name);

        if (!server.users.containsKey(name)) {
            Player player = PlayerService.create(name, server.game);
            String worldStr = WoldService.string(server.game.world);
            String playerStr = PlayerService.string(player);

            server.game.players.put(name, player);
            server.users.put(name, user);

            server.sender.send(user, Parse.build(Rule.TRU, playerStr));
            server.sender.send(user, Parse.build(Rule.MAP, worldStr));
            server.sender.sendOther(user, Parse.build(Rule.CON, playerStr));
            server.sender.sendAnother(user);

            UserService.message(user, "CON");
        } else {
            server.sender.send(user, Rule.FLS.name());
        }
    }

    public void newDisconnection(DatagramPacket packet) {
        String name = Parse.getMes(packet);

        User user = server.users.get(name);
        Player player = server.game.players.get(name);

        WoldService.remove(server.game, player);
        server.users.remove(name);
        server.game.players.remove(name);

        server.sender.sendOther(user, Parse.build(Rule.DIS, name));
        UserService.message(user, "DIS");
    }

    public void updateState(DatagramPacket packet) {
        String mes = Parse.getMes(packet);
        String name = mes.substring(0, mes.indexOf(":"));
        State state = State.valueOf(mes.substring(mes.indexOf(":") + 1, mes.length()));

        User user = server.users.get(name);
        Player player = server.game.players.get(name);

        if (!user.isReady()) {
            server.game.move(player, state);
            String playerStr = PlayerService.string(player);
            server.sender.sendAll(Parse.build(Rule.STA, playerStr));
        }

        user.setReady(true);

        if (UserService.allReady(server.users)) {
            String map = Parse.build(Rule.MAP, WoldService.string(server.game.world));
            server.sender.sendAll(map);
            UserService.offReady(server.users);
        }
    }

}
