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

public class Eventer {

    private Server server;

    public Eventer(Server server) {
        this.server = server;
    }

    public void newConnection(DatagramPacket packet) {
        String name = Parse.getMes(packet);
        User user = new User(packet);

        if (!UserService.exist(server.users, name)) {
            Player player = PlayerService.create(name, server.game);
            user.setPlayer(player);
            String world = WoldService.string(server.game.world);
            server.sender.send(user, Parse.build(Rule.TRU, PlayerService.string(player)));
            server.sender.sendOther(user, Parse.build(Rule.CON, PlayerService.string(player)));
            server.users.forEach(u -> server.sender.send(user, Parse.build(Rule.CON, PlayerService.string(u.getPlayer()))));
            server.sender.send(user, Parse.build(Rule.MAP, world));
            server.users.add(user);
        } else {
            server.sender.send(user, Rule.FLS.name());
        }
    }

    public void newDisсonnection(DatagramPacket packet) {
        String name = Parse.getMes(packet);
        User user = UserService.find(server.users, name);
        UserService.delete(server.users, user.getId());
        WoldService.remove(server.game.world, user.getPlayer());
        server.sender.sendOther(user, Parse.build(Rule.DIS, name));
    }

    public void updateState(DatagramPacket packet) {
        String mes = Parse.getMes(packet);
        String name = mes.substring(0, mes.indexOf(":"));
        State state = State.valueOf(mes.substring(mes.indexOf(":") + 1, mes.length()));

        User user = UserService.find(server.users, name);

        if (!user.isReady()) {
            WoldService.move(server.game.world, user.getPlayer(), state);
            String player = PlayerService.string(user.getPlayer());
            server.sender.sendAll(Parse.build(Rule.STA, player));
        }

        user.setReady(true);

        if (UserService.allReady(server.users)) {
            String map = Parse.build(Rule.MAP, WoldService.string(server.game.world));
            server.sender.sendAll(map);
            UserService.offReady(server.users);
        }
    }

}
