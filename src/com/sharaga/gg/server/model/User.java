package com.sharaga.gg.server.model;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class User {

    private String player;
    private DatagramPacket packet;
    private InetAddress address;
    private int port;
    private boolean ready;

    public User(DatagramPacket packet, String player) {
        this.player = player;
        this.packet = packet;
        this.address = packet.getAddress();
        this.port = packet.getPort();
        ready = false;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
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

        if (port != user.port) return false;
        if (ready != user.ready) return false;
        if (player != null ? !player.equals(user.player) : user.player != null) return false;
        if (packet != null ? !packet.equals(user.packet) : user.packet != null) return false;
        return address != null ? address.equals(user.address) : user.address == null;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + (packet != null ? packet.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (ready ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                ", player='" + player + '\'' +
                ", packet=" + packet +
                ", address=" + address +
                ", port=" + port +
                ", ready=" + ready +
                '}';
    }

}
