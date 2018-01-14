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

			while (!socket.isOutputShutdown()) {
				System.out.print("Message = ");
				String message = br.readLine();
				out.writeUTF(message);
           		out.flush();
			}
		} catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}