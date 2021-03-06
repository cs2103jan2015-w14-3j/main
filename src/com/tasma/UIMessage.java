/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

/**
 * Provides UI messages
 */
public final class UIMessage {
    public static final String COMMAND_ADD_SUCCESS =
            "Your task \"%s\" has been successfully created.";
    public static final String COMMAND_ADD_ARG_EMPTY =
            "The 'add' command requires the details of the task you wish to add."
            + " Try:\n\tadd <details>\nIf you need help, you can type 'help add'"
            + " to get help on the command.";
    public static final String COMMAND_ADD_UNDO =
            "Your task \"%s\" has been undone.";
    
    public static final String COMMAND_MARK_SUCCESS = 
            "Your task \"%s\" has been marked as done.";
    public static final String COMMAND_MARK_MULTIPLE_SUCCESS = 
            "%d of your tasks have been marked as done.";
    public static final String COMMAND_MARK_NOTFOUND = 
            "The task(s) you were looking to mark done was not found.";
    public static final String COMMAND_MARK_UNDO = 
            "Your task \"%s\" has been marked as undone.";
    public static final String COMMAND_MARK_MULTIPLE_UNDO = 
            "%d of your tasks been marked as undone.";
    
    public static final String COMMAND_UNMARK_SUCCESS = 
            "Your task \"%s\" has been marked as undone.";
    public static final String COMMAND_UNMARK_MULTIPLE_SUCCESS = 
            "%d of your tasks have been marked as undone.";
    public static final String COMMAND_UNMARK_NOTFOUND = 
            "The task(s) you were looking to mark undone was not found.";
    public static final String COMMAND_UNMARK_UNDO = 
            "Your task \"%s\" has been marked as done.";
    public static final String COMMAND_UNMARK_MULTIPLE_UNDO = 
            "%d of your tasks have been marked as done.";

    public static final String COMMAND_DELETE_SUCCESS = 
            "Your task \"%s\" has been deleted.";
    public static final String COMMAND_DELETE_MULTIPLE_SUCCESS = 
            "%d of your tasks have been deleted.";
    public static final String COMMAND_DELETE_NOTFOUND = 
            "The task(s) you were looking to delete was not found.";
    public static final String COMMAND_DELETE_UNDO = 
            "Your task \"%s\" has been undeleted.";
    public static final String COMMAND_DELETE_MULTIPLE_UNDO = 
            "%d of your tasks have been undeleted.";

    public static final String COMMAND_EDIT_SUCCESS = 
            "Your task \"%s\" has been successfully updated.";
    public static final String COMMAND_EDIT_NOTFOUND = 
            "The task you were looking to edit was not found.";
    public static final String COMMAND_EDIT_ARG_EMPTY = 
            "The 'edit' command requires the task ID and details of the task "
            + "you wish to update with. Try:\n\tedit <number> <details>\n"
            + "If you need help, you can type `help edit` to get help on the command.";
    public static final String COMMAND_EDIT_UNDO = 
            "Changes to your task \"%s\" has been undone.";

    public static final String COMMAND_SEARCH_EMPTY_QUERY = 
            "The 'search' command needs a query to find tasks for you. Enter the"
            + "`help search` command for more information on 'search'.\n"
            + "If you need help, you can type `help search` to get help on the command.";
    public static final String COMMAND_SEARCH_RESULT = 
            "%d result(s) found for \"%s\".";

    public static final String COMMAND_LIST_RESULT = "%d task(s) upcoming.";

    public static final String COMMAND_SET_SUCCESS = 
            "Configuration value for %s has been updated.";
    public static final String COMMAND_SET_UNDO = 
            "Configuration value for %s has been restored.";

    public static final String COMMAND_ALIAS_UPDATED = 
            "Alias command %s has been updated.";
    public static final String COMMAND_ALIAS_RESTORE = 
            "Alias command %s has been restored.";
    public static final String COMMAND_ALIAS_REMOVE = 
            "Alias command %s has been removed.";

    public static final String COMMAND_INVALID = 
            "TASMA could not understand what you said. If you need help, "
            + "enter the 'help' command and I will be glad to assist you.";
    public static final String COMMAND_EXCEPTION = 
            "An unexpected error has occurred: %s";

    public static final String TASK_LIST_EMPTY = 
            "No tasks!";
    
    public static final String COMMAND_BOX_PLACEHOLDER = 
            "What would you like to do?";

    public static final String SECTION_HEADER_FLOATING = "%d Floating";
    public static final String SECTION_HEADER_OVERDUE = "%d Overdue";
    public static final String SECTION_HEADER_TODAY = "%d Today";
    public static final String SECTION_HEADER_TOMORROW = "%d Tomorrow";
    public static final String SECTION_HEADER_REMAINING = "%d Upcoming";
    public static final String SECTION_HEADER_DONE = "%d Done";
    
    public static final String HEADER_TASK_TOMORROW = "Tasks due Tomorrow";
    public static final String HEADER_TASK_TODAY = "Tasks due Today";
    public static final String HEADER_TASK_OVERDUE = "Overdue Tasks";
    public static final String HEADER_TASK_DONE = "Completed Tasks";
    public static final String HEADER_TASK_UNDONE = "Task List";
    public static final String HEADER_TASK_UPCOMING = "Upcoming Tasks";
    public static final String HEADER_TASK_FLOATING = "Floating Tasks";
    public static final String HEADER_TASK_SEARCH = "Tasks matching \"%s\"";
}
