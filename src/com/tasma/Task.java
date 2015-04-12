/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * The Task Model
 * holds the details of a task that can be serialised.
 */
public class Task implements Cloneable {
	private String details = "";
	private DateTime startDateTime;
	private DateTime endDateTime;
	private boolean isDone;

	private static final int DEFAULT_HOURS = 23;
	private static final int DEFAULT_MINUTES = 59;
	private static final String STRING_PM = "pm";
	private static final String STRING_AM = "am";

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
			DateTimeFormatter fmt;

			if (d.toLocalDate().equals(new LocalDate())) {
				date = "today";
			} else {
				fmt = DateTimeFormat.forPattern("dd-MM-yy");
				date = d.toString(fmt);
			}

			if (d.getHourOfDay() == DEFAULT_HOURS && d.getMinuteOfHour() == DEFAULT_MINUTES) {
				return date;
			} else {
				fmt = DateTimeFormat.forPattern(", h:mma");
				date += d.toString(fmt);
			}
			return date;
		}
	}

	public String getFormattedStartDateTime() {
		return getFormattedDateTime(startDateTime);
	}

	public String getFormattedEndDateTime() {
		return getFormattedDateTime(endDateTime);
	}

	private String getFormattedDateTime(DateTime d) {
		String date;
		DateTimeFormatter fmt;

		if (d.toLocalDate().equals(new LocalDate())) {
			date = "today";
		} else {
			fmt = DateTimeFormat.forPattern("d MMM");
			date = d.toString(fmt);
			
			if (d.getYear() != new DateTime().getYear()) {
				fmt = DateTimeFormat.forPattern(", yyyy");
				date += d.toString(fmt);
			}
		}

		if (d.getHourOfDay() == DEFAULT_HOURS && d.getMinuteOfHour() == DEFAULT_MINUTES) {
			return date;
		} else {
			fmt = DateTimeFormat.forPattern(", h:mma");
			date += d.toString(fmt);
		}
		return date;
	}


	/*private String getStringDate(DateTime d, String date) {
		if (d.toLocalDate().equals(new LocalDate())) {
			date = "today";
		} else {
			date += String.valueOf(String.format("%02d", d.getDayOfMonth())) + "-" + 
					String.valueOf(String.format("%02d", d.getMonthOfYear())) + "-" + 
					String.valueOf(d.getYear() % 100);
		}
		return date;
	}*/

	/*private String getStringTime(DateTime d, String date) {
		if (d.getHourOfDay() == DEFAULT_HOURS && d.getMinuteOfHour() == DEFAULT_MINUTES) {
			return date;
		} else { //has date and time
			date += ", ";
			DateTimeFormatter fmt = DateTimeFormat.forPattern("h:mma");
			date += d.toString(fmt);

			return date;
		}
	}*/

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
		if (isDone) {
			return TaskState.DONE;
		}
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
