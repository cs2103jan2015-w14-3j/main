/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma;

public class UIMessage {
	public static final String COMMAND_ADD_SUCCESS = "Your task \"%s\" has been successfully created.";
	public static final String COMMAND_ADD_ARG_EMPTY = "The <add> command requires the details of the task you wish to add. Try:\n\tadd <details>";
	public static final String COMMAND_ADD_UNDO = "Your task \"%s\" has been undone.";
	
	public static final String COMMAND_MARK_SUCCESS = "Your task \"%s\" has been marked as done.";
	public static final String COMMAND_MARK_NOTFOUND = "The task you were looking to mark done was not found.";
	public static final String COMMAND_MARK_ARG_EMPTY = "The <mark> command requires the task ID of the task you wish to mark done. Try:\n\tmark <number>";
	public static final String COMMAND_MARK_UNDO = "Your task \"%s\" has been marked as undone.";

	public static final String COMMAND_ARCHIVE_SUCCESS = "Your task \"%s\" has been archived.";
	public static final String COMMAND_ARCHIVE_NOTFOUND = "The task you were looking to archive was not found.";
	public static final String COMMAND_ARCHIVE_ARG_EMPTY = "The <archive> command requires the task ID of the task you wish to archive. Try:\n\tarchive <number>";
	public static final String COMMAND_ARCHIVE_UNDO = "Your task \"%s\" has been unarchived.";

	public static final String COMMAND_EDIT_SUCCESS = "Your task \"%s\" has been successfully updated.";
	public static final String COMMAND_EDIT_NOTFOUND = "The task you were looking to edit was not found.";
	public static final String COMMAND_EDIT_ARG_EMPTY = "The <edit> command requires the task ID and details of the task you wish to update with. Try:\n\tedit <number> <details>";
	public static final String COMMAND_EDIT_UNDO = "Changes to your task \"%s\" has been undone.";

	public static final String COMMAND_SEARCH_RESULT = "%d result(s) found for \"%s\".";

	public static final String COMMAND_LIST_RESULT = "%d task(s) upcoming.";

	public static final String COMMAND_SET_SUCCESS = "Configuration value for %s has been updated.";
	public static final String COMMAND_SET_UNDO = "Configuration value for %s has been restored.";
	
	public static final String COMMAND_INVALID = "The command you have entered is invalid. Please try again.";
	public static final String COMMAND_EXCEPTION = "An unexpected error has occurred: %s";
}
