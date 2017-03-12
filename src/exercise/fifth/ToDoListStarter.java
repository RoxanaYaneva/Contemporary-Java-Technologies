package exercise.fifth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListStarter {

	final Scanner scanner = new Scanner(System.in);

	private String menu = new StringBuilder().append("\nИзберете опция:\n")
			.append("1) Всички задачи, подредени по приоритет \n").append("2) Задачи със статус IN PROCESS\n")
			.append("3) Задачи, които да се завършат в следващите три дни\n")
			.append("4) Импортиране на задачи от файл\n").append("5) Експортиране на задачи във файл\n")
			.append("6) Изход\n").toString();

	private List<Task> searchArray;

	public ToDoListStarter(List<Task> searchArray) {
		this.searchArray = searchArray;
	}

	public void start() throws IOException {
		boolean exit = false;

		while (!exit) {
			showMenu();
			promptUser();
			try {
				int chosenOption = Integer.parseInt(scanner.nextLine());
				if (chosenOption < 1 || chosenOption > 6) {
					throw new IllegalArgumentException();
				}
				final ToDoList toDoListSearcher = new ToDoList(searchArray);
				List<Task> resultTasksArray = new ArrayList<>();
				switch (chosenOption) {
				case 1: {
					resultTasksArray = toDoListSearcher.getTaskByPriority();
					break;
				}
				case 2: {
					resultTasksArray = toDoListSearcher.getTasksInProcess();
					break;
				}
				case 3: {
					resultTasksArray = toDoListSearcher.getTasksThatHaveToBeFinishedInThreeDays();
					break;
				}
				case 4: {
					toDoListSearcher.importTasksFromFile(getFileName());
					break;
				}
				case 5: {
					toDoListSearcher.exportTasksToFile(getFileName());
					break;
				}
				case 6:
					exit = true;
				}
				ToDoList toDoList = new ToDoList(resultTasksArray);
				toDoList.printList();
			} catch (IllegalArgumentException e) {
				System.out.println("Грешен вход. Опитайте отново!");
			}
		}
		scanner.close();
	}

	private String getFileName() {
		System.out.println("\nВъведете име на файл: ");
		String fileName = scanner.nextLine();
		return fileName;
	}

	private void promptUser() {
		System.out.println("\nИзберете опция (1-6):");
	}

	private void showMenu() {
		System.out.println(menu);
	}
}
