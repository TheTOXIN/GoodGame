package Server;

public class Run {
    public static void main(String[] args) {
        Server server = new Server(2504);
        server.start();
    }
}
