import java.io.*;
import java.net.*;

public class Connector {
	public static void main(String[] args) throws InterruptedException {
		try {
			Socket socket = new Socket("localhost", 2504);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());

			System.out.println("Connect to server...");
			System.out.println("Input message: ");

			new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						while (!socket.isInputShutdown()) {
							String message = in.readUTF();
							System.out.println("Message:" + message);
						}
					} catch (IOException e) {
						System.out.println("Server otvalilsya");
            			e.printStackTrace();
        			}
				}
			}).start();

			while (!socket.isOutputShutdown()) {
				String message = br.readLine();
				out.writeUTF(message);
           		out.flush();
           		System.out.println("TEST");
			}
        } catch (Exception e) {
        	System.out.println("SUKA BLYAT");
            e.printStackTrace();
        }
	}
}