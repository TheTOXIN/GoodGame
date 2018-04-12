package com.sharaga.gg.server;

public class Run {
    public static void main(String[] args) {
        Server server = new Server(8080);
        server.start();
    }
}
