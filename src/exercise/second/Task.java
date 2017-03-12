package exercise.second;

import java.time.LocalDate;

class Task implements Comparable<Task> {

	private String name;
	private String taskDescription;
	private Status status;
	private int priority;
	private LocalDate deadline;

	public Task(String name, String taskDescription, Status status, int priority, LocalDate deadline) {
		this.name = name;
		this.taskDescription = taskDescription;
		this.status = status;
		this.priority = priority;
		this.deadline = deadline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "Name : " + name + ", status : " + status + ", priority : " + priority + ", deadline : " + deadline;
	}

	@Override
	public int compareTo(Task other) {
		if (this.priority > other.priority) {
			return 1;
		} else if (this.priority < other.priority) {
			return -1;
		}
		return 0;
	}
}