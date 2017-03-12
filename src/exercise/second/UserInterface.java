package exercise.second;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {

	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		Task task1 = new Task("C++", "Learn exceptions", Status.IN_PROCESS, 4, LocalDate.of(2016, 11, 5));
		Task task2 = new Task("Java", "Learn collections API", Status.DONE, 2, LocalDate.of(2016, 11, 4));
		Task task3 = new Task("C++", "Learn pointers", Status.INITIAL, 5, LocalDate.of(2016, 11, 6));
		Task task4 = new Task("C++", "Read about unit tests", Status.IN_PROCESS, 5, LocalDate.of(2016, 11, 3));
		Task task5 = new Task("Java", "Watch tutorials about threads and multithreading", Status.INITIAL, 5,
				LocalDate.of(2016, 11, 8));
		Task task6 = new Task("C++", "Read about TDD", Status.IN_PROCESS, 2, LocalDate.of(2016, 11, 15));
		Task task7 = new Task("C++", "Implement mergesort algorithm", Status.DONE, 3, LocalDate.of(2016, 10, 20));

		// varargs declared constructor
		ToDoList list = new ToDoList(task1, task2, task3, task4, task5, task6, task7);

		Task task8 = new Task("Java", "Implement mergesort algorithm", Status.DONE, 3, LocalDate.of(2016, 10, 25));
		Task task9 = new Task("C++", "Implement BFS algorithm", Status.IN_PROCESS, 1, LocalDate.of(2016, 12, 25));
		Task task10 = new Task("C++", "Write C++ homework", Status.INITIAL, 2, LocalDate.of(2016, 11, 7));
		// Adding tasks to list
		list.setList(list.addTask(task8));
		list.setList(list.addTask(task9));
		list.setList(list.addTask(task10));

		menu();

		String option, filename;
		boolean waitingForOption = true;

		while (waitingForOption) {
			System.out.print("\n\tEnter your command: ");
			try {
				option = in.nextLine();
				switch (option) {
				case "1":
					ToDoList priorityList = new ToDoList();
					priorityList.setList(list.getTasksByPriority());
					priorityList.print();
					menu();
					break;
				case "2":
					ToDoList tasksInProcess = new ToDoList();
					tasksInProcess.setList(list.getTasksInProcess());
					if (tasksInProcess.emptyList()) {
						System.out.print("\nThere are no tasks IN PROCESS.");
					}
					tasksInProcess.print();
					menu();
					break;
				case "3":
					ToDoList tasksInThreeDays = new ToDoList();
					tasksInThreeDays.setList(list.getTasksThatHaveToBeFinishedInThreeDays());
					if (tasksInThreeDays.emptyList()) {
						System.out.print("\nThere are no tasks that need to be done in three days.");
					}
					tasksInThreeDays.print();
					menu();
					break;
				case "4":
					System.out.println("\nEnter the name of the file /import/: ");
					filename = in.nextLine();
					// method load
					menu();
					break;
				case "5":
					System.out.println("\nEnter the name of the file /export/: ");
					filename = in.nextLine();
					list.saveTasks(filename);
					menu();
					break;
				case "6":
					waitingForOption = false;
					System.out.println("\nEnd of program.");
					break;
				default:
					throw new IllegalArgumentException("\nWrong input! Try again.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void menu() {
		System.out.println("\n\t\tMENU\n");
		System.out.println("\t(1) Get tasks by priority.");
		System.out.println("\t(2) Get tasks in process.");
		System.out.println("\t(3) Get tasks that need to be done in three days.");
		System.out.println("\t(4) Load tasks from file.");
		System.out.println("\t(5) Save tasks in file.");
		System.out.println("\t(6) Exit");
	}
}
