package Server;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class User {
    private static int count = 0;
    private final int id;
    private DatagramPacket packet;
    private String name;
    private InetAddress address;
    private int port;

    public User(String name, DatagramPacket packet) {
        this.id = ++count;
        this.packet = packet;
        this.name = name;
        this.address = packet.getAddress();
        this.port = packet.getPort();
    }

    public int getId() {
        return id;
    }

    public DatagramPacket getPacket() {
        return packet;
    }

    public void setPacket(DatagramPacket packet) {
        this.packet = packet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                ", port=" + port +
                '}';
    }
}
