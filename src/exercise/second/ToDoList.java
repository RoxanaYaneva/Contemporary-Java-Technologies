package exercise.second;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

class ToDoList {

	private Task[] list;

	public ToDoList(Task... list) {
		this.list = list;
	}

	public Task[] addTask(Task task) {
		Task[] newList = new Task[list.length + 1];
		for (int i = 0; i < list.length; i++) {
			newList[i] = list[i];
		}
		newList[list.length] = task;
		return newList;
	}

	public Task[] getList() {
		return list;
	}

	public void setList(Task[] list) {
		this.list = list;
	}

	public Task[] getTasksByPriority() {
		Arrays.sort(list);
		return list;
	}

	public Task[] getTasksInProcess() {
		final Task[] tasksInProcess = new Task[list.length];
		int newIndex = 0;
		for (int i = 0; i < list.length; i++) {
			final Task task = list[i];
			if (task.getStatus().equals(Status.IN_PROCESS)) {
				tasksInProcess[newIndex] = task;
			}
		}
		final Task[] resTaskList = new Task[newIndex];
		System.arraycopy(tasksInProcess, 0, resTaskList, 0, newIndex);
		return resTaskList;
	}

	public Task[] getTasksThatHaveToBeFinishedInThreeDays() {
		Task[] tasksByThreeDays = new Task[this.list.length];
		final LocalDate today = LocalDate.now();
		final LocalDate dateAfterThreeDays = today.plusDays(4);
		int newIndex = 0;
		for (int i = 0; i < this.list.length; i++) {
			final Task task = list[i];
			if (!task.getStatus().equals(Status.DONE) && task.getDeadline().isAfter(dateAfterThreeDays)
					&& task.getDeadline().isAfter(today)) {
				tasksByThreeDays[newIndex++] = task;
			}
		}

		final Task[] resList = new Task[newIndex];
		System.arraycopy(tasksByThreeDays, 0, resList, 0, newIndex);

		Arrays.sort(resList);
		return resList;
	}

	public Task[] loadTasks(String filename) throws FileNotFoundException, IOException {
		String wholeTask;
		int newIndex = 0;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
			while ((wholeTask = bufferedReader.readLine()) != null) {
				String components[] = wholeTask.split(",");
				Status status = Status.valueOf(components[2]);
				int priority = Integer.parseInt(components[3]);
				LocalDate deadline = LocalDate.parse(components[4]);
				this.list[newIndex++] = new Task(components[0], components[1], status, priority, deadline);
			}
		}
		return this.list;
	}

	public void saveTasks(String filename) throws IOException {
		try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filename))) {
			for (Task task : this.list) {
				fileWriter.write(task.toString());
				fileWriter.newLine();
			}
		}
	}

	boolean emptyList() {
		return list.length == 0;
	}

	public void print() {
		System.out.println();
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
	}
}
