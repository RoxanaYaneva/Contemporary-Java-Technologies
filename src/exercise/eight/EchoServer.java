package exercise.eight;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer implements AutoCloseable {

	public static final int SERVER_PORT = 34678;
	private ServerSocket serverSocket;

	public EchoServer(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
	}

	public void startRunning() {
		System.out.println("Echo server is listening on port " + SERVER_PORT);
		try {
			while (true) {
				Socket socket = this.serverSocket.accept();
				ClientHandler handler = new ClientHandler(socket);
				handler.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		if (this.serverSocket != null) {
			try {
				this.serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		try (EchoServer echoServer = new EchoServer(SERVER_PORT)) {
			echoServer.startRunning();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
