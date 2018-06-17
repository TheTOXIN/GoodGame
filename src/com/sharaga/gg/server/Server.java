package com.sharaga.gg.server;

import com.sharaga.gg.server.model.User;
import com.sharaga.gg.utill.Parse;
import com.sharaga.gg.utill.Rule;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    //TODO make map
    public List<User> users = new ArrayList<>();
    public Game game;
    public Eventer eventer;
    public Sender sender;
    public DatagramSocket socket;
    public boolean isRunning = false;

    public Server(Game game, int port) {
        try {
            this.game = game;
            this.socket = new DatagramSocket(port);
            this.eventer = new Eventer(this);
            this.sender = new Sender(this);
        } catch (SocketException e ) {
            System.out.println("Socket - ERROR");
        }
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
            eventer.newDisсonnection(packet);
        } else if (rule == Rule.STA) {
            eventer.updateState(packet);
        }
    }

}
