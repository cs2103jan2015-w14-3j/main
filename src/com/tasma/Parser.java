package com.tasma;

/**
 * @author Manika Agarwal
 *
 */

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
		if (!taskDetails.contains("on") || !taskDetails.contains("at")) {
			parsedTask.setDetails(taskDetails);  //command does not contain when or where details
			taskDetails = EMPTY_STRING;
		} else {
			
		}
	}
	
	private void getWhere() {
		
	}

	private void getWhen() {

	}
}
