/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class Task implements Cloneable {
	private String details = "";
	private DateTime startDateTime;
	private DateTime endDateTime;
	private boolean isDone;

	public Task(String details) {
		this.details = details;
	}

	public String getDetails() {
		return details;
	}

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

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public Task clone() throws CloneNotSupportedException {
		return (Task) super.clone();
	}

	//@author A0118888J
	@Override
	public String toString(){
		String result = "";
		result += details;

		if (startDateTime != endDateTime) {
			result += " from " + getStringStartDateTime() + " to " + getStringEndDateTime();
		} else if (startDateTime != null) {
			result += " on " + getStringStartDateTime();
		}
		
		return result; 
	}
}
