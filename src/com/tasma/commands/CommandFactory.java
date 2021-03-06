/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import java.util.LinkedList;

import com.tasma.InvalidInputException;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.ui.TasmaUserInterface;

/**
 * Provides building of commands for execution
 */
public class CommandFactory {

    protected TasmaUserInterface userInterface;
    protected TaskCollection collection;
    protected LinkedList<Task> currentState = new LinkedList<Task>();
    
    public CommandFactory(TasmaUserInterface userInterface,
    		TaskCollection collection) {
        this.userInterface = userInterface;
        this.collection = collection;
    }

    public CommandInterface getCommand(String input) throws InvalidInputException {
        CommandInterface result = null;
        InputSplitter splitter = new InputSplitter(input);
        String command = splitter.next();

        CommandType commandType = AliasHandler.normalize(command);
        switch (commandType) {
            case ADD:
                result = new AddCommand(userInterface, collection, currentState, splitter.remainder());
                break;
            case SEARCH:
                result = new SearchCommand(userInterface, collection, currentState, splitter.remainder());
                break;
            case LIST:
                result = new ListCommand(userInterface, collection, currentState, splitter.remainder());
                break;
            case MARK:
                result = new MarkCommand(userInterface, collection, currentState, normalizeInputRange(splitter.remainder()));
                break;
            case UNMARK:
                result = new UnmarkCommand(userInterface, collection, currentState, normalizeInputRange(splitter.remainder()));
                break;
            case EDIT:
                result = new EditCommand(userInterface, collection, currentState, normalizeIndexInput(splitter.next()), splitter.remainder());
                break;
            case DELETE:
                result = new DeleteCommand(userInterface, collection, currentState, normalizeInputRange(splitter.remainder()));
                break;
            case SET:
                result = new SetCommand(userInterface, collection, splitter.next(), splitter.remainder());
                break;
            case ALIAS:
                result = new AliasCommand(userInterface, collection, splitter.next(), splitter.remainder());
                break;
            case HELP:
                result = new HelpCommand(userInterface, collection, splitter.remainder());
                break;
            case TUTORIAL:
                result = new TutorialCommand(userInterface, collection);
                break;
            case EXIT:
                result = new ExitCommand(userInterface, collection);
                break;
            default:
                // probably an invalid command
                result = new InvalidCommand(userInterface, collection, input);
                break;
        }
        return result;
    }
    
    protected LinkedList<Integer> normalizeInputRange(String condition)
    		throws InvalidInputException {
        LinkedList<Integer> result = new LinkedList<Integer>();
        String[] parts = condition.split("\\s*(,|\\s)\\s*");
        for (String s : parts) {
            try {
                if (s.indexOf("-") == -1) {
                    int index = Integer.parseInt(s) - 1;
                    result.add(index);
                } else {
                    // has a range in it
                    String[] range = s.split("-");
                    if (range.length != 2) {
                        throw new InvalidInputException();
                    }
                    int lowerBound = Integer.parseInt(range[0]);
                    int upperBound = Integer.parseInt(range[1]);
                    for (int j = lowerBound - 1; j < upperBound; ++j) {
                        result.add(j);
                    }
                }
            } catch (NumberFormatException ex) {
            }
        }
        return result;
    }
    
    protected int normalizeIndexInput(String strIndex) throws InvalidInputException {
        int result = -1;
        try {
            result = Integer.parseInt(strIndex) - 1;
        } catch (NumberFormatException ex) {
        }

        return result;
    }
}
