package Client;

import Utill.Parse;
import Utill.Rule;

/**
 * Основной класс для управления пакетами
 */
public class Service {
    private Connector con;
    private Player player;

    public Service(Connector con, Player player) {
        this.con = con;
        this.player = player;
    }

    public void login() {
        con.send(Parse.build(Rule.CON, player.getName()));
        Rule answer = Parse.getRule(con.receive());
        con.isConnected = answer == Rule.TRU;
    }

    public void start() {
        listener();
        informer();
    }

    public void listener() {
        new Thread(() -> {
            while (con.isConnected) {
                String data = con.receive();
                String message = Parse.getMes(data);
                Rule rule = Parse.getRule(data);

                if (Rule.MES == rule) {
                    Player p = Mapper.toPlayer(message);
                    if (Main.players.contains(p)) {
                        Main.players.remove(p);
                    }
                    Main.players.add(p);
                }
            }
        }).start();
    }

    public void informer() {
        new Thread(() -> {
            while (con.isConnected) {
                try {
                    Thread.sleep(10);
                    String message = Mapper.toString(player);
                    con.send(Parse.build(Rule.MES, message));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
