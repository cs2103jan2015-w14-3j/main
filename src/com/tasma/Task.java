/**
 * Tasma Task Manager
 */
package com.tasma;

import org.joda.time.LocalDate;

public class Task implements Cloneable {
	private String taskId = "";
	private String details = "";
	private String location = "";
	private LocalDate startDateTime;
	private LocalDate endDateTime;
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

	public LocalDate getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDate startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDate getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDate endDateTime) {
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
	public String toString(){
		return details + " at " + location + " on " + endDateTime.toString(); 
	}
	
    @Override
    protected Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }
}
