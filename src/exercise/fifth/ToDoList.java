package exercise.fifth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToDoList {

	private List<Task> list;

	public ToDoList() {
		this.list = new ArrayList<Task>();
	}

	public ToDoList(List<Task> list) {
		this.list = list;
	}

	public List<Task> getList() {
		return new ArrayList<Task>(this.list);
	}

	public void setList(List<Task> list) {
		this.list = list;
	}

	public void addTask(Task task) {
		this.list.add(task);
	}

	public List<Task> getTaskByPriority() {
		List<Task> tasksByPriority = new ArrayList<>(this.list);
		Collections.sort(tasksByPriority);
		return tasksByPriority;
	}

	public List<Task> getTasksInProcess() {
		List<Task> tasksInProcess = new ArrayList<>();
		for (Task task : this.list) {
			if (task.getStatus().equals(TaskStatus.IN_PROCESS)) {
				tasksInProcess.add(task);
			}
		}
		Collections.sort(tasksInProcess);
		return tasksInProcess;
	}

	public List<Task> getTasksThatHaveToBeFinishedInThreeDays() {
		List<Task> tasksByThreeDays = new ArrayList<>();
		final LocalDate today = LocalDate.now();
		final LocalDate dateAfterThreeDays = today.plusDays(4);
		for (Task task : this.list) {
			if ((task.getStatus().equals(TaskStatus.IN_PROCESS) || task.getStatus().equals(TaskStatus.INITIAL))
					&& task.getDeadline().isBefore(dateAfterThreeDays) && task.getDeadline().isAfter(today)) {
				tasksByThreeDays.add(task);
			}
		}
		Collections.sort(tasksByThreeDays);
		return tasksByThreeDays;
	}

	public void importTasksFromFile(String path) throws IOException {
		Path filePath = Paths.get(path);
		if (!Files.exists(filePath)) {
			throw new FileNotFoundException();
		}
		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				final Task task = Task.convertToTask(line);
				this.list.add(task);
			}
		}
	}

	public void exportTasksToFile(String path) throws IOException {
		Path filePath = Paths.get(path);
		if (!Files.exists(filePath)) {
			throw new FileNotFoundException();
		}
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
			for (Task task : this.list) {
				writer.write(task.toString());
				writer.write(System.lineSeparator());
			}
		}
		copyFile(filePath);
	}

	private void copyFile(Path filePath) throws IOException {
		Path copyFile = Paths.get(filePath.getParent() + "/" + "tasks-copy.txt");
		Files.copy(filePath, copyFile, StandardCopyOption.REPLACE_EXISTING);
	}

//	private void backup(Path filePath) throws IOException {
//		Path backupFolder = Paths.get(filePath.getParent() + "/" + LocalDate.now());
//		Files.createDirectories(backupFolder);
//		Files.copy(filePath, backupFolder);
//	}

	public void printList() {
		for (Task task : this.list) {
			System.out.println(task.toString());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDoList other = (ToDoList) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
}
