/**
 * Tasma Task Manager
 */
package com.tasma;

import org.joda.time.DateTime;

public class Task implements Cloneable {
	private String details = "";
	private String location = "";
	private DateTime startDateTime;
	private DateTime endDateTime;
	private boolean isDone;
	private boolean isArchived;

	public Task() {

	}

	public Task(String taskId, String details) {
		this.details = details;
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

	public String getStringEndDateTime() {
		if (endDateTime == null) {
			return ""; 
		} else { 
			String date = String.valueOf(String.format("%02d", endDateTime.getDayOfMonth())) + "-" + 
					String.valueOf(String.format("%02d", endDateTime.getMonthOfYear())) + "-" + 
					String.valueOf(endDateTime.getYear() % 100);

			if (endDateTime.getHourOfDay() == 0 && endDateTime.getMinuteOfHour() == 0) {
				return date;
			} else { //has date and time
				date += " at ";

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
		if (endDateTime != null) {
			result += " on " + getStringEndDateTime();
		}
		if (location.length() > 0) {
			result += " at " + location;
		}
		
		return result; 
	}
}
