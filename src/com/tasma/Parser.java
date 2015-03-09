package com.tasma;

/**
 * @author Manika Agarwal
 *
 */

import org.joda.time.LocalDate;
import org.joda.time.DateTimeConstants;

public class Parser {
	private Task parsedTask;

	private static final String EMPTY_STRING = "";
	private static String taskDetails; 

	public Task parse(String details) {
		parsedTask = new Task();
		taskDetails = details;

		getWhat();

		getWhen();

		getWhere();

		return parsedTask;
	}


	private void getWhat() {
		final String[] keywords = {"on", "at", "in"};
		int index = 0; 

		if (!taskDetails.contains("on") || !taskDetails.contains("at")) {  //command does not contain when or where details
			taskDetails = EMPTY_STRING;
			//still in preliminary phases as task may not contain 'on' or 'at' but still have when and/or where details
		} else {
			for (int i = 0; i < keywords.length; i++) {
				if (taskDetails.contains(keywords[i])) {
					index = taskDetails.indexOf(keywords[i]);		
				}
			}
		}

		parsedTask.setDetails(taskDetails);
		taskDetails = taskDetails.substring(index); 
	}

	private void getWhen() {
		if (taskDetails != EMPTY_STRING) {
			final String[] keywords = {"on", "from", "at", "by"},
					daysOfWeek = {"mon", "tues", "wed", "thur", "fri", "sat", "sun"};

			if (taskDetails.contains(keywords[0])) {  //date
				int indexOn = taskDetails.indexOf(keywords[0]);

				if (taskDetails.contains(keywords[1])) {
					int indexFrom = taskDetails.indexOf(keywords[1]);
					String dateOfTask = taskDetails.substring(indexOn + 3, indexFrom - 1);

					int indexNext = 0, addWeek = 0;

					if (dateOfTask.contains("next")) {
						indexNext = taskDetails.indexOf("next") + "next".length();
						addWeek = 1;
					}

					String date = taskDetails.substring(indexNext);

					for (int i = 0; i < daysOfWeek.length; i++) {
						if (date.contains(daysOfWeek[i])) {
							LocalDate d = new LocalDate();
							d = d.plusWeeks(addWeek);
							d = d.withDayOfWeek(DateTimeConstants.FRIDAY);

							parsedTask.setEndDateTime(d);
						}
					}
				} else if (taskDetails.contains(keywords[2])) {
					int indexAt = taskDetails.indexOf(keywords[2]);
				}
			}			
		}
	}

	private void getWhere() {

	}	
}
