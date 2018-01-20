package Client;

import Utill.Parse;
import Utill.Rule;

/**
 * Основной класс для управления пакетами
 */
public class Service {
    private Connector con;

    public Service(Connector con) {
        this.con = con;
    }

    public void start() {
        listener();
    }

    public void login() {
        con.send(Parse.build(Rule.CON, Main.player.getName()));
        String data = con.receive();

        Rule answer = Parse.getRule(data);
        con.isConnected = (answer == Rule.TRU);
    }

    public void listener() {
        new Thread(() -> {
            while (con.isConnected) {
                String data = con.receive();

                String message = Parse.getMes(data);
                Rule rule = Parse.getRule(data);

                if (Rule.STA == rule) {
                    refresh(message);
                }
            }
        }).start();
    }

    public void refresh(String message) {
        Player mapper = Mapper.toPlayer(message);

        if (!Main.players.contains(mapper)) {
            Main.players.add(mapper);
        }

        for (Player p : Main.players) {
            if (mapper.equals(p)) {
                p.setState(mapper.getState());
            }
        }
    }

    public void informer() {
        new Thread(() -> {
            String message = Mapper.toString(Main.player);
            con.send(Parse.build(Rule.STA, message));
        }).start();
    }
}
