package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exercise.fifth.Task;
import exercise.fifth.TaskStatus;
import exercise.fifth.ToDoList;

public class ToDoListTest {

	private ToDoList toDoList = new ToDoList();

	@Before
	public void initialize() {
		toDoList.addTask(new Task("C++", "Learn exceptions", TaskStatus.IN_PROCESS, 4, LocalDate.of(2016, 11, 5)));
		toDoList.addTask(new Task("Java", "Learn collections API", TaskStatus.DONE, 2, LocalDate.of(2016, 11, 4)));
		toDoList.addTask(new Task("C++", "Learn pointers", TaskStatus.INITIAL, 5, LocalDate.of(2016, 11, 6)));
		toDoList.addTask(new Task("C++", "Read about TDD", TaskStatus.IN_PROCESS, 1, LocalDate.of(2016, 11, 15)));
	}

	@Test
	public void testConvertToTask() {
		String line = "Study for the test,NOW,INITIAL,1,2016-12-24";
		Task task = new Task("Study for the test", "NOW", TaskStatus.INITIAL, 1, LocalDate.of(2016, 12, 24));
		assertTrue(task.equals(Task.convertToTask(line)));
	}

	@Test
	public void testGetTasksByPriority() {
		List<Task> taskByPriority = new ArrayList<>();
		taskByPriority.add(new Task("C++", "Read about TDD", TaskStatus.IN_PROCESS, 1, LocalDate.of(2016, 11, 15)));
		taskByPriority.add(new Task("Java", "Learn collections API", TaskStatus.DONE, 2, LocalDate.of(2016, 11, 4)));
		taskByPriority.add(new Task("C++", "Learn exceptions", TaskStatus.IN_PROCESS, 4, LocalDate.of(2016, 11, 5)));
		taskByPriority.add(new Task("C++", "Learn pointers", TaskStatus.INITIAL, 5, LocalDate.of(2016, 11, 6)));
		assertTrue(taskByPriority.equals(toDoList.getTaskByPriority()));
	}

	@Test
	public void testGetTasksInProcess() {
		List<Task> tasksInProcess = new ArrayList<>();
		tasksInProcess.add(new Task("C++", "Read about TDD", TaskStatus.IN_PROCESS, 1, LocalDate.of(2016, 11, 15)));
		tasksInProcess.add(new Task("C++", "Learn exceptions", TaskStatus.IN_PROCESS, 4, LocalDate.of(2016, 11, 5)));
		assertTrue(tasksInProcess.equals(toDoList.getTasksInProcess()));
	}

	@Test
	public void testGetTasksThatHaveToBeDoneInThreeDays() {
		LocalDate date = LocalDate.now();
		toDoList.addTask(new Task("C++", "Read about TDD", TaskStatus.IN_PROCESS, 1, date.plusDays(3)));
		List<Task> tasksToBeDoneInThreeDays = new ArrayList<>();
		tasksToBeDoneInThreeDays.add(new Task("C++", "Read about TDD", TaskStatus.IN_PROCESS, 1, date.plusDays(3)));
		assertTrue(tasksToBeDoneInThreeDays.equals(toDoList.getTasksThatHaveToBeFinishedInThreeDays()));
	}

	@Test
	public void testImportTasksFromFile() throws IOException {
		ToDoList expectedToDoList = new ToDoList();
		expectedToDoList.importTasksFromFile("test.txt");
		assertEquals(expectedToDoList.getList(), toDoList.getList());
	}
}
