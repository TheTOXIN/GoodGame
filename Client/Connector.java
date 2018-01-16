import java.io.*;
import java.net.*;

public class Connector {

	private Player player;

	public Connector(Player player) {
		this.player = player;
	}

	public void connect() throws InterruptedException {
		try {
			Socket socket = new Socket("localhost", 2504);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());

			System.out.println("Connect to server...");

			new Thread(() -> {
                try {
                    while (!socket.isInputShutdown()) {
                    	String str = in.readUTF();
                        Player p = Mapper.toPlayer(str);

						if (Main.players.contains(p)) {
							Main.players.remove(p);
						}
						Main.players.add(p);
					}
                } catch (Exception e) {
                    System.out.println("Server otvalilsya");
                    e.printStackTrace();
                }
            }).start();

			while (!socket.isOutputShutdown()) {
				String str = Mapper.toString(player);
				out.writeUTF(str);
           		out.flush();
           		Thread.sleep(10);
			}
        } catch (Exception e) {
        	System.out.println("SUKA BLYAT");
            e.printStackTrace();
        }
	}
}