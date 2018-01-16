import java.io.*;
import java.net.*;
import java.util.*;

public class Connector {
	public static void main(String[] args) throws InterruptedException {
		try {

			ServerSocket server = new ServerSocket(2504);
			System.out.println("Server START!!!");

			while (true) {
				Socket client = server.accept();
				Eventer event = new Eventer(client);

				Eventer.clients.add(event);

				Thread thread = new Thread(event);
				thread.start();
				
				System.out.println(
					"Connection #" + 
					event.number + 
					" all(" + 
					Eventer.clients.size() + 
					")"
				);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}