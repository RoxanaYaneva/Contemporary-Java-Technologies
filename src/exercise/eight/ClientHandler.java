package exercise.eight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

	private Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
			String message;
			while (true) {
				message = bufferedReader.readLine();
				if (message.equals(Client.CLOSE_COMMAND)) {
					break;
				}
				if (message.equals("count")) {
					// printWriter.println("Number of active clients: ");
					// printWriter.flush();
				} else if (message != null) {
					printWriter.println("Echo server: " + message);
					printWriter.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
