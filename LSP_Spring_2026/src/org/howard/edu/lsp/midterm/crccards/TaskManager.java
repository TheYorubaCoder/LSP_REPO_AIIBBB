package org.howard.edu.lsp.midterm.crccards;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages a collection of tasks, supporting adding, finding,
 * and filtering tasks by status.
 *
 * @author Ibukunoluwa Adeloye
 * @version 1.0
 */
public class TaskManager {
	private HashMap<String, Task> tasks = new HashMap<>();

	/**
	 * Adds a new task to the manager. If a task with the same ID already
	 * exists, an exception is thrown.
	 *
	 * @param task the task to add
	 * @throws IllegalArgumentException if a task with the same ID already exists
	 */
	public void addTask(Task task) {
		String taskId = task.getTaskId();
		if (tasks.get(taskId) == null) {
			tasks.put(taskId, task);
		} else {
			throw new IllegalArgumentException("Task with ID " + taskId + " already exists");
		}
	}

	/**
	 * Finds and returns the task with the given ID, or null if not found.
	 *
	 * @param taskId the ID of the task to find
	 * @return the task with the given ID, or null if no such task exists
	 */
	public Task findTask(String taskId) {
		return tasks.get(taskId);
	}

	/**
	 * Returns a list of all tasks that match the given status.
	 *
	 * @param status the status to filter tasks by (e.g. "OPEN", "IN_PROGRESS", "COMPLETE")
	 * @return a list of tasks with the specified status, or an empty list if none are found
	 */
	public List<Task> getTasksByStatus(String status) {
		List<Task> tasksByStatus = new ArrayList<>();
		for (Map.Entry<String, Task> entry : tasks.entrySet()) {
			Task task = entry.getValue();
			String taskStatus = task.getStatus();

			if (taskStatus.equals(status)) {
				tasksByStatus.add(task);
			}
		}
		return tasksByStatus;
	}
}