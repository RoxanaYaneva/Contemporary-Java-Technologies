package exercise.fourth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Converter {

	private Dictionary dictionary;
	private BufferedWriter writer;
	private boolean endOfStream;

	public Converter() {
		try {
			this.dictionary = new Dictionary();
			this.writer = new BufferedWriter(new FileWriter("cyrillic.txt"));
			this.endOfStream = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String toCyrillicLetter(Character c) {
		String character = this.dictionary.getLetter(c);
		if (character == null) {
			character = c.toString();
		}
		return character;
	}

	public void convert(InputStream readStream) throws IOException {
		InputStreamReader inputStreamReader = null;
		InputStream inputStream = null;
		try {
			byte[] byteArray = toByteArray(readStream);
			inputStream = new ByteArrayInputStream(byteArray);
			inputStreamReader = new InputStreamReader(new ByteArrayInputStream(byteArray));
			if (isEndOfStream(inputStream)) {
				this.endOfStream = true;
				return;
			}
			int c;
			while ((c = inputStreamReader.read()) != -1) {
				String cyrillicLetter = toCyrillicLetter((char) c);
				this.writer.write(cyrillicLetter);
			}
			this.writer.newLine();
			this.writer.flush();
		} catch (IOException e) {
			this.closeStream();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
			inputStreamReader.close();
		}
	}

	private byte[] toByteArray(InputStream readStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(readStream));
		String line = bufferedReader.readLine();
		return line.getBytes();
	}

	private void closeStream() throws IOException {
		if (this.writer != null) {
			this.writer.close();
		}
	}

	public boolean isEndOfStream() {
		return this.endOfStream;
	}

	private boolean isEndOfStream(InputStream stream) {
		try (Scanner scanner = new Scanner(stream)) {
			return scanner.next().equals("kraj");
		}
	}

	private static String makeString() throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		try (FileReader fileReader = new FileReader("cyrillic.txt")) {
			int c;
			while ((c = fileReader.read()) != -1) {
				if (Character.isLetter(c)) {
					stringBuilder.append(c);
				} else if (c == ' ' || c == '\n') {
					stringBuilder.append(' ');
				}
			}
		}
		return stringBuilder.toString();
	}

	public static int countWordsInText() throws IOException {
		String text = makeString();
		String[] words = text.split(" ");
		return words.length;
	}
}
