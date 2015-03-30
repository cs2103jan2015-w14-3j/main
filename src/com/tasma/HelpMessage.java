package com.tasma;

public final class HelpMessage {
	public static final String HELP_TEMPLATE = "Help information about <%s> command\n\n%s\n\nAliases:\n%s";
	
	public static final String HELP_ADD = "\tadd <task details>\n\nThe <add> command creates a new task for you based on the details you have entered. After entering the command, the list will be refreshed to show the new task in the list.";

	public static final String HELP_SEARCH = "\tsearch <query>\n\nThe <search> command finds tasks in your to-do list that matches the query string you have entered. After entering the command, the result of the search operation will be shown in the list.";
}
