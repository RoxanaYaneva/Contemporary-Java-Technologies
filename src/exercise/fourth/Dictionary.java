package exercise.fourth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Dictionary {

	private Map<Character, String> dictionary;

	public Dictionary() throws IOException {
		this.dictionary = new HashMap<>();
		this.fillDictionary();
	}

	public Map<Character, String> getDictionary() {
		return new HashMap<>(this.dictionary);
	}

	public void setDictionary(Map<Character, String> dictionary) {
		this.dictionary = dictionary;
	}

	private void fillDictionary() throws IOException {
		Properties properties = new Properties();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("symbols.properties"))) {
			properties.load(bufferedReader);
			for (String word : properties.stringPropertyNames()) {
				this.dictionary.put(word.charAt(0), properties.getProperty(word));
			}
		}
	}

	public String getLetter(Character c) {
		return this.dictionary.get(c);
	}
}
