package org.howard.edu.lsp.midterm.crccards;

import java.util.Arrays;

/**
 * Represents a task with an ID, description, and status.
 * Valid statuses are "OPEN", "IN_PROGRESS", and "COMPLETE".
 * 
 * @author Ibukunoluwa Adeloye
 * @version 1.0
 */

public class Task {
	
	private String taskId;
	private String description;
	private String status = "OPEN";
	private String[] validStatuses = {"OPEN", "IN_PROGRESS", "COMPLETE"};
	
	/**
	 * Constructs a new Task with the given ID and description.
	 * Status defaults to "OPEN".
	 * 
	 * @param taskId      the unique identifier for the task
	 * @param description a brief description of the task
	 */
	public Task(String taskId, String description)
	{
		this.taskId = taskId;
		this.description = description;
	}
	
	/**
	 * Returns the unique identifier of the task.
	 * 
	 * @return the task ID
	 */
	public String getTaskId() {
		return taskId;
	}
	
	/**
	 * Returns the description of the task.
	 * 
	 * @return the task description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns the current status of the task.
	 * 
	 * @return the task status
	 */
	public String getStatus() {
		return status;
	}
		
	/**
	 * Sets the status of the task. If the provided status is not one of
	 * "OPEN", "IN_PROGRESS", or "COMPLETE", the status is set to "UNKNOWN".
	 * 
	 * @param status the desired status to set
	 */
	public void setStatus(String status) {
		if (Arrays.asList(validStatuses).contains(status))
		{
			this.status =status;
		}
		else {
			this.status ="UNKNOWN";
		}
		
		
	}
	
	/**
	 * Returns a string representation of the task in the format:
	 * taskId description [status]
	 * 
	 * @return formatted string representation of the task
	 */
	public String toString() {
		return taskId + " " + description + " " + "[" + status + "]";
		
	}


}
