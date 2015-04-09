/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * The Task Model
 * holds the details of a task that can be serialised.
 */
public class Task implements Cloneable {
	private String details = "";
	private DateTime startDateTime;
	private DateTime endDateTime;
	private boolean isDone;

	public Task(String details) {
		this.details = details;
	}

	public Task() {
		this("");
	}

	/**
	 * Get the details of the task
	 * @return A string containing the details of the task
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Set the details of the task
	 * @param details The new details of the task
	 */
	public void setDetails(String details) {
		this.details = details;
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

	//@author A0118888J
	public String getStringStartDateTime() {
		return getStringDateTime(startDateTime);
	}

	public String getStringEndDateTime() {
		return getStringDateTime(endDateTime);
	}
	
	public String getStringDateTime(DateTime d) {
		if (d == null) {
			return ""; 
		} else { 
			String date;
			
			if (d.toLocalDate().equals(new LocalDate())) {
				date = "today";
			} else {
				date = String.valueOf(String.format("%02d", d.getDayOfMonth())) + "-" + 
						String.valueOf(String.format("%02d", d.getMonthOfYear())) + "-" + 
						String.valueOf(d.getYear() % 100);
			}
			
			if (d.getHourOfDay() == 23 && d.getMinuteOfHour() == 59) {
				return date;
			} else { //has date and time
				date += ", ";
				if (d.getHourOfDay() == 0) { //for 12am, but currently cannot reach here
					date += "12" + ":" + String.valueOf(String.format("%02d", d.getMinuteOfHour())) + "am";
				} else if (d.getHourOfDay() > 0 && d.getHourOfDay() < 12) { 
					date += String.valueOf(d.getHourOfDay()) + ":" + 
							String.valueOf(String.format("%02d", d.getMinuteOfHour())) + "am";
				} else if (d.getHourOfDay() == 12) {
					date += String.valueOf(d.getHourOfDay()) + ":" + 
							String.valueOf(String.format("%02d", d.getMinuteOfHour())) + "pm";
				} else { //if (endDateTime.getHourOfDay() > 12)
					date += String.valueOf(endDateTime.getHourOfDay() - 12) + ":" + 
							String.valueOf(String.format("%02d", d.getMinuteOfHour())) + "pm";
				}
			} 

			return date;
		}
	}
	
	//@author A0132763H
	public void setEndDateTime(DateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	/**
	 * Check if this task is done
	 * @return
	 */
	public boolean isDone() {
		return isDone;
	}

	/**
	 * Set the task to be done or undone
	 * @param isDone True if the task is done, and false if it is not.
	 */
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	/**
	 * Performs a clone operation to get a copy of the task with the same values.
	 */
	@Override
	public Task clone() throws CloneNotSupportedException {
		return (Task) super.clone();
	}

	//@author A0118888J
	@Override
	public String toString() {
		String result = "";
		result += details;

		if (startDateTime != endDateTime) {
			result += " from " + getStringStartDateTime() + " to " + getStringEndDateTime();
		} else if (startDateTime != null) {
			result += " on " + getStringStartDateTime();
		}
		
		return result; 
	}
	
	//@author A0132763H
	/**
	 * Get the type of the task
	 * @return Returns a value from the enum TaskType
	 */
	public TaskType getType() {
		if (startDateTime == null) {
			return TaskType.FLOATING;
		} else if (startDateTime.equals(endDateTime)) {
			return TaskType.DEADLINE;
		}
		return TaskType.TIMED;
	}
	
	/**
	 * Get the state of the task
	 * @return Returns a value from the enum TaskState
	 */
	public TaskState getState() {
		if (startDateTime == null) {
			return TaskState.FLOATING;
		}
		DateTime dateTimeNow = new DateTime();
		DateTime dateTimeTmrStart = dateTimeNow.plusDays(1).withTime(0, 0, 0, 0);
		DateTime dateTimeTmrEnd = dateTimeNow.plusDays(2).withTime(0, 0, 0, 0);
		if (endDateTime.isBefore(dateTimeNow)) {
			return TaskState.OVERDUE;
		} else if (endDateTime.isBefore(dateTimeTmrStart)) {
			return TaskState.TODAY;
		} else if (endDateTime.isBefore(dateTimeTmrEnd)) {
			return TaskState.TOMORROW;
		}
		return TaskState.UPCOMING;
	}
}
