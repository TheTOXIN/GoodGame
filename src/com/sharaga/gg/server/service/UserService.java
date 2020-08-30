package com.sharaga.gg.server.service;

import com.sharaga.gg.server.model.User;

import java.util.List;

public class UserService {

    public static boolean exist(List<User> users, String name) {
        return users.stream().anyMatch(user -> user.getPlayer().getName().equals(name));
    }

    public static User find(List<User> users, String name) {
        return users.stream().filter(user -> user.getPlayer().getName().equals(name)).findFirst().orElse(null);
    }

    public static boolean allReady(List<User> users) {
        return users.stream().allMatch(User::isReady);
    }

    public static void offReady(List<User> users) {
        users.forEach(user -> user.setReady(false));
    }

    public static void delete(List<User> users, int id) {
        users.stream().filter(user -> user.getId() == id).findFirst().ifPresent(users::remove);
    }
}
