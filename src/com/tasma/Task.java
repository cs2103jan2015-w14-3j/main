/**
 * Tasma Task Manager
 */
//@author A0132763
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
		if (startDateTime == null) {
			return ""; 
		} else { 
			String date;
			
			if (startDateTime.toLocalDate().equals(new LocalDate())) {
				date = "today";
			} else {
				date = String.valueOf(String.format("%02d", startDateTime.getDayOfMonth())) + "-" + 
						String.valueOf(String.format("%02d", startDateTime.getMonthOfYear())) + "-" + 
						String.valueOf(startDateTime.getYear() % 100);
			}
			if (startDateTime.getHourOfDay() == 0 && startDateTime.getMinuteOfHour() == 0) {
				return date;
			} else { //has date and time
				date += ", ";
				if (startDateTime.getHourOfDay() == 0) { //for 12am, but currently cannot reach here
					date += "12" + ":" + String.valueOf(String.format("%02d", startDateTime.getMinuteOfHour())) + "am";
				} else if (startDateTime.getHourOfDay() > 0 && startDateTime.getHourOfDay() < 12) { 
					date += String.valueOf(startDateTime.getHourOfDay()) + ":" + 
							String.valueOf(String.format("%02d", startDateTime.getMinuteOfHour())) + "am";
				} else if (startDateTime.getHourOfDay() == 12) {
					date += String.valueOf(startDateTime.getHourOfDay()) + ":" + 
							String.valueOf(String.format("%02d", startDateTime.getMinuteOfHour())) + "pm";
				} else { //if (endDateTime.getHourOfDay() > 12)
					date += String.valueOf(startDateTime.getHourOfDay() - 12) + ":" + 
							String.valueOf(String.format("%02d", startDateTime.getMinuteOfHour())) + "pm";
				}
			} 

			return date;
		}
	}

	public String getStringEndDateTime() {
		if (endDateTime == null) {
			return ""; 
		} else { 
			String date;
			
			if (endDateTime.toLocalDate().equals(new LocalDate())) {
				date = "today";
			} else {
				date = String.valueOf(String.format("%02d", endDateTime.getDayOfMonth())) + "-" + 
						String.valueOf(String.format("%02d", endDateTime.getMonthOfYear())) + "-" + 
						String.valueOf(endDateTime.getYear() % 100);
			}
			
			if (endDateTime.getHourOfDay() == 0 && endDateTime.getMinuteOfHour() == 0) {
				return date;
			} else { //has date and time
				date += ", ";
				if (endDateTime.getHourOfDay() == 0) { //for 12am, but currently cannot reach here
					date += "12" + ":" + String.valueOf(String.format("%02d", endDateTime.getMinuteOfHour())) + "am";
				} else if (endDateTime.getHourOfDay() > 0 && endDateTime.getHourOfDay() < 12) { 
					date += String.valueOf(endDateTime.getHourOfDay()) + ":" + 
							String.valueOf(String.format("%02d", endDateTime.getMinuteOfHour())) + "am";
				} else if (endDateTime.getHourOfDay() == 12) {
					date += String.valueOf(endDateTime.getHourOfDay()) + ":" + 
							String.valueOf(String.format("%02d", endDateTime.getMinuteOfHour())) + "pm";
				} else { //if (endDateTime.getHourOfDay() > 12)
					date += String.valueOf(endDateTime.getHourOfDay() - 12) + ":" + 
							String.valueOf(String.format("%02d", endDateTime.getMinuteOfHour())) + "pm";
				}
			} 

			return date;
		}
	}
	
	//@author A0132763
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
		} else if (startDateTime == endDateTime) {
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
		LocalDate dateNow = new LocalDate();
		LocalDate dateTmr = dateNow.plusDays(1);
		if (endDateTime.toLocalDate().isBefore(dateNow)) {
			return TaskState.OVERDUE;
		} else if (endDateTime.toLocalDate().equals(dateNow)) {
			return TaskState.TODAY;
		} else if (endDateTime.toLocalDate().equals(dateTmr)) {
			return TaskState.TOMORROW;
		}
		return TaskState.UPCOMING;
	}
}
