package com.sharaga.gg.server;

import com.sharaga.gg.server.model.User;
import com.sharaga.gg.utill.Parse;
import com.sharaga.gg.utill.Rule;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private static Server servak;

    public Map<String, User> users = new HashMap<>();
    public Game game;
    public Eventer eventer;
    public Sender sender;
    public DatagramSocket socket;
    public boolean isRunning;

    private Server() { }

    public static Server setInstance(Game game, int port) {
        Server.servak = new Server();

        try {
            Server.servak.isRunning = false;
            Server.servak.game = game;
            Server.servak.socket = new DatagramSocket(port);
            Server.servak.eventer = new Eventer();
            Server.servak.sender = new Sender();
        } catch (SocketException e ) {
            System.out.println("Socket - ERROR");
        }

        return Server.servak;
    }

    public static Server getInstance() {
        return Server.servak;
    }

    public void start() {
        System.out.println("...SERVER = START...");
        isRunning = true;
        game.start();
        receive();
    }

    private void receive() {
        new Thread(() -> {
            while (isRunning) {
                byte[] data = new byte[1024];
                DatagramPacket packet = new DatagramPacket(data, data.length);

                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    System.out.println("Receive - ERROR");
                }

                process(packet);
            }
        }).start();
    }

    private void process(DatagramPacket packet) {
        Rule rule = Parse.getRule(Parse.getStr(packet));

        if (rule == Rule.CON) {
            eventer.newConnection(packet);
        } else if (rule == Rule.DIS) {
            eventer.newDisconnection(packet);
        } else if (rule == Rule.STA) {
            eventer.updateState(packet);
        }
    }

}
