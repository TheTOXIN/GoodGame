import java.io.*;
import java.net.*;

public class Connector {
	public static void main(String[] args) throws InterruptedException {
		try {

			ServerSocket server = new ServerSocket(2504);
			System.out.println("Server START!!!");

			while (true) {
				Socket client = server.accept();
				Thread thread = new Thread(new Eventer(client));
				thread.start();
				
				System.out.println("Connection accpeted");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class Eventer implements Runnable {
		private Socket client;

		public Eventer(Socket client) {
			this.client = client;
		}

		@Override
		public void run() {
			try {
				DataOutputStream out = new DataOutputStream(client.getOutputStream());
				DataInputStream in = new DataInputStream(client.getInputStream());
			
				while (!client.isClosed()) {
					String entry = in.readUTF();
					System.out.println("Message: " + entry);
				}
				
				System.out.println("Disconnected");

				in.close();
				out.close();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}