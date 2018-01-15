import java.io.*;
import java.net.*;
import java.util.*;

public class Eventer implements Runnable {
	public static List<Eventer> clients = new ArrayList<>();
	public static int count = 0;
	
	private Socket client;
	private DataOutputStream out;
	private DataInputStream in;
	public int number;

	public Eventer(Socket client) {
		this.client = client;
		count++;
		number = count;
	}

	@Override
	public void run() {
		try {
			this.out = new DataOutputStream(client.getOutputStream());
			this.in = new DataInputStream(client.getInputStream());

			while (!client.isClosed()) {
				String message = in.readUTF();
				System.out.println("Client-" + number+ " = " + message);
				sender(message);
			}

			in.close();
			out.close();
			client.close();
		} catch (IOException e) {
			clients.remove(this);
			System.out.println("Disconnected #" + number + " all(" + clients.size() + ")");
		}
	}

	public void sender(String message) {
		try {
			for (Eventer client : clients) {
				client.out.writeUTF(message);
				client.out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
