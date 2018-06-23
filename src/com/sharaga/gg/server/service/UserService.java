package com.sharaga.gg.server.service;

import com.sharaga.gg.server.model.User;

import java.util.Map;

public class UserService {

    public static boolean allReady(Map<String, User> users) {
        return users.entrySet().stream().map(Map.Entry::getValue).allMatch(User::isReady);
    }

    public static void offReady(Map<String, User> users) {
        users.entrySet().stream().map(Map.Entry::getValue).forEach(u -> u.setReady(false));
    }

    public static void message(User user, String msg) {
        System.out.println(msg + " = " + user.getPlayer() + " " + user.getAddress() + ":" + user.getPort());
    }

}
