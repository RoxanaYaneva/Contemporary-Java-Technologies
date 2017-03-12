package exercise.fifth;

import java.time.LocalDate;

public class Task implements Comparable<Task> {

	private String title;
	private String descrption;
	private TaskStatus status;
	private int priority;
	private LocalDate deadline;

	public Task(String title, String descrption, TaskStatus status, int priority, LocalDate deadline) {
		this.title = title;
		this.descrption = descrption;
		this.status = status;
		this.priority = priority;
		this.deadline = deadline;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return title + "," + descrption + "," + status + "," + priority + "," + deadline;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result + ((descrption == null) ? 0 : descrption.hashCode());
		result = prime * result + priority;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Task other = (Task) obj;
		if (deadline == null) {
			if (other.deadline != null)
				return false;
		} else if (!deadline.equals(other.deadline))
			return false;
		if (descrption == null) {
			if (other.descrption != null)
				return false;
		} else if (!descrption.equals(other.descrption))
			return false;
		if (priority != other.priority)
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public static Task convertToTask(String taskString) {
		String[] components = taskString.split(",");
		String title = components[0];
		String description = components[1];
		TaskStatus status = TaskStatus.valueOf(components[2]);
		int priority = Integer.parseInt(components[3]);
		LocalDate date = LocalDate.parse(components[4]);

		return new Task(title, description, status, priority, date);
	}

	@Override
	public int compareTo(Task otherTask) {
		if (this.priority > otherTask.priority) {
			return 1;
		} else if (this.priority < otherTask.priority) {
			return -1;
		}
		return 0;
	}
}
