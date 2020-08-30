package com.sharaga.gg.server;

import com.sharaga.gg.server.model.Player;
import com.sharaga.gg.server.model.User;
import com.sharaga.gg.server.service.PlayerService;
import com.sharaga.gg.server.service.UserService;
import com.sharaga.gg.server.service.WoldService;
import com.sharaga.gg.utill.Const;
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

        System.out.println(name + " - TRY CONNECT : " + packet.getAddress());

        if (!UserService.exist(Server.users, name)) {
            Player player = PlayerService.create(name, server.game);
            user.setPlayer(player);
            String world = WoldService.string(server.game.world);
            server.sender.send(user, Parse.build(Rule.TRU, PlayerService.string(player)));
            server.sender.sendOther(user, Parse.build(Rule.CON, PlayerService.string(player)));
            Server.users.forEach(u -> server.sender.send(user, Parse.build(Rule.CON, PlayerService.string(u.getPlayer()))));
            server.sender.send(user, Parse.build(Rule.MAP, world));
            Server.users.add(user);

            System.out.println(name + " - CONNECT : TRUE ");
        } else {
            server.sender.send(user, Rule.FLS.name());

            System.out.println(name + " - CONNECT : FALSE ");
        }
    }

    public void newDisсonnection(DatagramPacket packet) {
        String name = Parse.getMes(packet);
        User user = UserService.find(Server.users, name);
        UserService.delete(Server.users, user.getId());
        WoldService.remove(server.game.world, user.getPlayer());
        server.sender.sendOther(user, Parse.build(Rule.DIS, name));

        System.out.println(name + " - DISCONNECT : " + packet.getAddress());
    }

    public void updateState(DatagramPacket packet) {
        String mes = Parse.getMes(packet);

        String[] parse = Parse.parseDelimer(mes);

        String name = parse[0];
        State state = State.valueOf(parse[1]);

        User user = UserService.find(Server.users, name);

        if (!user.isReady()) {
            server.game.move(user.getPlayer(), state);
            String player = PlayerService.string(user.getPlayer());
            server.sender.sendAll(Parse.build(Rule.STA, player));
        }

        user.setReady(true);

        if (UserService.allReady(Server.users)) {
            String world = WoldService.string(server.game.world);
            String map = Parse.build(Rule.MAP, world);

            server.sender.sendAll(map);
            UserService.offReady(Server.users);
        }
    }
}
