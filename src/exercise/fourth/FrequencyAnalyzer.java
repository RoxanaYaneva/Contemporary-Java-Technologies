package exercise.fourth;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FrequencyAnalyzer {

	private static Map<Character, Integer> hMap = new HashMap<>();

	private static void fillMap() throws IOException {

		try (FileReader fileReader = new FileReader("cyrillic.txt")) {
			int c;
			while ((c = fileReader.read()) != -1) {
				if (Character.isLetter(c)) {
					char key = (char) c;
					if (hMap.containsKey(key)) {
						hMap.put(key, hMap.get(key) + 1);
					} else {
						hMap.put(key, 1);
					}
				}
			}
		}
	}

	public static void frequencyAnalysis() throws IOException {
		fillMap();
		List<Entry<Character, Integer>> list = new ArrayList<>(hMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
			@Override
			public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		for (Iterator<Entry<Character, Integer>> iterator = list.iterator(); iterator.hasNext();) {
			Entry<Character, Integer> entry = iterator.next();
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}
