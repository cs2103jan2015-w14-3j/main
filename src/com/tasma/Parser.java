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
		int index = 0;
		
		if (!taskDetails.contains("on") || !taskDetails.contains("at")) {  //command does not contain when or where details
			taskDetails = EMPTY_STRING;
			//still in preliminary phases as task may not contain 'on' or 'at' but still have when and/or where details
		} else if (taskDetails.contains("on")) {
			index = taskDetails.indexOf("on");			
		} else if (taskDetails.contains("at")) {
			index = taskDetails.indexOf("at");
		}
		
		parsedTask.setDetails(taskDetails);
		taskDetails = taskDetails.substring(index); 
	}
	
	private void getWhen() {
		
	}
	
	private void getWhere() {
		
	}	
}
