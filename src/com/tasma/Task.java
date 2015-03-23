/**
 * Tasma Task Manager
 */
package com.tasma;

import org.joda.time.DateTime;

public class Task implements Cloneable {
	private String taskId = "";
	private String details = "";
	private String location = "";
	private DateTime startDateTime;
	private DateTime endDateTime;
	private boolean isDone;
	private boolean isArchived;

	public Task() {

	}

	public Task(String taskId, String details) {
		this.taskId = taskId;
		this.details = details;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public DateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(DateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public DateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(DateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	@Override
	public Task clone() throws CloneNotSupportedException {
		return (Task) super.clone();
	}
	
	@Override
	public String toString(){
		String result = "";
		result += details;
		if (location != null) {
			result += " at " + location;
		}
		if (endDateTime != null) {
			result += " on " + endDateTime;
		}
		return result; 
	}
}
