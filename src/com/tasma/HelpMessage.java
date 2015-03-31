package com.tasma;

public final class HelpMessage {
	public static final String HELP_TEMPLATE = "Help information about '%s' command\n\n"
			+ "%s\n\n"
			+ "Aliases:\n%s\n\n"
			+ "Angular brackets <> denote parameters which are required; Square brackets [] denote optional parameters.";
	
	public static final String HELP_ADD = "\tadd <task details>\n\n"
			+ "The 'add' command creates a new task for you based on the details you have entered. After entering the command, the list will be refreshed to show the new task in the list.";

	public static final String HELP_SEARCH = "\tsearch <query>\n\n"
			+ "The 'search' command finds tasks in your to-do list that matches the query string you have entered. After entering the command, the result of the search operation will be shown in the list.";

	public static final String HELP_LIST = "\tlist [filters]\n\n"
			+ "The 'list' command lists all the tasks that are upcoming in your to-do list. The [filter] parameter is optional and can be: done or past.";

	public static final String HELP_MARK = "\tmark <task number>\n\n"
			+ "The 'mark' command marks a specified task as done. The [task number] parameter is the number corresponding to the task that you want to select.";

	public static final String HELP_DELETE = "\tdelete <task number>\n\n"
			+ "The 'delete' command deletes a specified task from the list of tasks. The [task number] parameter is the number corresponding to the task that you want to select.";

	public static final String HELP_EDIT = "\tedit <task number> [task details]\n\n"
			+ "The 'edit' command allows you to modify details of a specific task. The [task number] parameter is the number corresponding to the task that you want to select. In the event that the new details of the task is not entered, the old details will be displayed in the command box for your convenience to edit.";

	public static final String HELP_SET = "\tset <config key> <value>\n\n"
			+ "The 'set' command sets configuration values that allows you to change the behaviour of Tasma. The <config key> is the configuration that you want to change and <value> is the new value.\n\n"
			+ "\tstorage: The path to the folder where the tasks.json storage file will be stored.\n";

	public static final String HELP_HELP = "\thelp <command>\n\n"
			+ "The 'help' command provides you information on how to use the various commands in Tasma. The <command> is the command to show help for and can be any of the following:\n\n"
			+ "\tadd:\tCreates a new task\n"
			+ "\tsearch:\tSearches for tasks\n"
			+ "\tlist:\tShows a list of upcoming tasks\n"
			+ "\tmark:\tMarks a task as done\n"
			+ "\tarchive:\tMoves a task to the archives\n"
			+ "\tedit:\tUpdates the details of a task\n"
			+ "\tset:\tSets values to application configuration\n"
			+ "\thelp:\tProvides help information like this one\n"
			+ "\ttutorial:\tShows the welcome tutorial again\n"
			+ "\texit:\tQuits the Tasma application\n";


	public static final String HELP_TUTORIAL = "\ttutorial\n\n"
			+ "The 'tutorial' command will launch the welcome tutorial that was shown when you first start Tasma.";

	public static final String HELP_EXIT = "\texit\n\n"
			+ "The 'exit' command will make Tasma quit its job and move to another city.";
}