package exercise.fourth;

import java.io.IOException;

public class Demo {

	public static void main(String[] args) throws IOException {

		System.out.println("Enter some text.");
		try {
			Converter converter = new Converter();
			while (!converter.isEndOfStream()) {
				converter.convert(System.in);
			}
			System.out.println("The number of words in the text is : " + Converter.countWordsInText());
			FrequencyAnalyzer.frequencyAnalysis();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
