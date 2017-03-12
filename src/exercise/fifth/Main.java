package exercise.fifth;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<Task> tasks = new ArrayList<>();
		
		tasks.add(new Task("C++", "Learn exceptions", TaskStatus.IN_PROCESS, 4, LocalDate.of(2016, 11, 5)));
		tasks.add(new Task("Java", "Learn collections API", TaskStatus.DONE, 2, LocalDate.of(2016, 11, 4)));
		tasks.add(new Task("C++", "Learn pointers", TaskStatus.INITIAL, 5, LocalDate.of(2016, 11, 6)));
		tasks.add(new Task("C++", "Read about unit tests", TaskStatus.IN_PROCESS, 5, LocalDate.of(2016, 11, 3)));
		tasks.add(new Task("Java", "Watch tutorials about threads and multithreading", TaskStatus.INITIAL, 5,
				LocalDate.of(2016, 11, 8)));
		tasks.add(new Task("C++", "Implement mergesort algorithm", TaskStatus.DONE, 3, LocalDate.of(2016, 10, 20)));
		tasks.add(new Task("C++", "Read about TDD", TaskStatus.IN_PROCESS, 2, LocalDate.of(2016, 11, 15)));

		try {
			new ToDoListStarter(tasks).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
