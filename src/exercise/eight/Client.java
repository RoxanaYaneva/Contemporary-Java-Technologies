package exercise.eight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private String localhost;
	private int port;
	public static final String CLOSE_COMMAND = "quit";

	public Client(String localhost, int port) {
		this.localhost = localhost;
		this.port = port;
	}

	public void startRunning() {
		System.out.println("Client is connected on port " + EchoServer.SERVER_PORT);
		try (Socket socket = new Socket(this.localhost, this.port);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				Scanner scanner = new Scanner(System.in);
				PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
			String message;
			do {
				message = scanner.nextLine();
				printWriter.println(message);
				printWriter.flush();
				System.out.println(bufferedReader.readLine());
			} while (!message.equals(CLOSE_COMMAND));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Client client = new Client("localhost", EchoServer.SERVER_PORT);
		client.startRunning();
	}
}
