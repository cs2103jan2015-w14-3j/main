/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tasma.commands.CommandFactory;
import com.tasma.commands.CommandInterface;
import com.tasma.commands.NotExecutedException;

/**
 * The main controller logic
 * Tip: Good, cheap, fast - choose any two. (:
 */
public class Controller {
	private static final Logger logger = Log.getLogger( Controller.class.getName() );

	private static final String COMMAND_UNDO = "undo";
	private static final String COMMAND_REDO = "redo";
	private static final String COMMAND_LIST = "list";
	
	/**
	 * The user interface to call the output methods from
	 */
	protected TasmaUserInterface userInterface;
	
	/**
	 * The task collection to work with
	 */
	protected TaskCollection collection;
	
	/**
	 * The command factory
	 */
	protected CommandFactory commandFactory;
	
	/**
	 * The history handling for undo/redo
	 */
	protected History history = new History();
	
	public Controller() throws Exception {
		this(new TaskCollection());
	}
	
	public Controller(TaskCollection collection) {
		this.collection = collection;
	}
	
	/**
	 * Performs initialization of the controller.
	 * The user interface (i.e. TasmaUserInterface implementation) must have been set via the setUserInterface method prior to calling this method.
	 * @throws Exception Thrown when the user interface for the controller is not set before initializing.
	 */
	public void initialize() throws Exception {
		if (userInterface == null) {
			Exception exception = new Exception("The user interface for the controller has not been set.");
			logger.log(Level.FINER, exception.toString(), exception);
			throw exception;
		}
		assert userInterface != null;
		collection.loadFromFile();
		commandFactory = new CommandFactory(userInterface, collection);
	}
	
	/**
	 * Set the user interface to be used by the controller.
	 * @param ui The interface to be called by the controller.
	 */
	public void setUserInterface(TasmaUserInterface ui) {
		assert ui != null;
		userInterface = ui;
		logger.log(Level.FINE, "Using {0} as the user interface now.", ui.getClass().getName());
	}
	
	/**
	 * Execute operations based on the user's input
	 * @param input The input string of the user
	 */
	public void executeInput(String input) {
		logger.log(Level.FINE, "Received input \"{0}\"", input);
		try {
			if (input.trim().equals(COMMAND_UNDO)) {
				history.undo();
				
				CommandInterface command = commandFactory.getCommand(COMMAND_LIST);
				command.execute();
			} else if (input.trim().equals(COMMAND_REDO)) {
				history.redo();
			} else {
				CommandInterface command = commandFactory.getCommand(input);
				command.execute();
				history.offer(command);
			}
		} catch (NotExecutedException nee) {
			
		} catch (Exception ex) {
			displayException(ex);
			userInterface.editCmdDisplay(input);
		}
	}
	
	public int getNumOfUndoneTasksByState(TaskState state){
		List<Task> tasksNotDone = collection.notDone();
		int numOverdue = 0;
		for (Task task : tasksNotDone) {
			if (task.getState() == state){
				numOverdue++;
			}
		}
		return numOverdue;
	}

	/**
	 * Displays an exception message to the user
	 * @param exception The exception to show
	 */
	protected void displayException(Exception exception) {
		assert exception != null;
		
		logger.log(Level.FINE, exception.toString(), exception);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_EXCEPTION, exception.getMessage()));
	}
}
