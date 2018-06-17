package com.sharaga.gg.server.model;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class User {

    private static int count = 0;
    private final int id;

    private Player player;
    private DatagramPacket packet;
    private InetAddress address;
    private int port;
    private boolean ready;

    public User() {
        this.id = ++count;
    }

    public User(DatagramPacket packet) {
        this.id = ++count;
        this.packet = packet;
        this.address = packet.getAddress();
        this.port = packet.getPort();
        ready = false;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public DatagramPacket getPacket() {
        return packet;
    }

    public void setPacket(DatagramPacket packet) {
        this.packet = packet;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (port != user.port) return false;
        if (player != null ? !player.equals(user.player) : user.player != null) return false;
        if (packet != null ? !packet.equals(user.packet) : user.packet != null) return false;
        return address != null ? address.equals(user.address) : user.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (packet != null ? packet.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + port;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick=" + player +
                ", packet=" + packet +
                ", address=" + address +
                ", port=" + port +
                '}';
    }

}
