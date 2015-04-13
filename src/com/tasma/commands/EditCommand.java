/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import java.util.List;

import com.tasma.Parser;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.UIMessage;
import com.tasma.ui.Palette;
import com.tasma.ui.TasmaUserInterface;

public class EditCommand extends AbstractUndoableCommand {

    protected String details;
    protected int index;
    protected List<Task> state;
    protected Task task;
    protected Task oldTaskDetails;
    
    public EditCommand(TasmaUserInterface userInterface,
            TaskCollection collection, List<Task> state,
            int index, String details) {
        super(userInterface, collection);
        this.state = state;
        this.details = details;
        this.index = index;
        try {
            this.task = state.get(index);
        } catch (IndexOutOfBoundsException ex) {
            
        }
        if (this.task == null) {
            userInterface.displayMessage(UIMessage.COMMAND_EDIT_NOTFOUND,
            		Palette.MESSAGE_WARNING);
        }
    }

    @Override
    public void execute() throws Exception {
        if (this.task == null) {
            throw new NotExecutedException();
        } else {
            if (details.equals("")) {
                userInterface.editCmdDisplay(
                		String.format("edit %d %s", index + 1, task.editString()));
            } else {
                oldTaskDetails = task.clone();
                
                Parser parser = new Parser();
                parser.parse(task, details);
                userInterface.displayMessage(
                		String.format(UIMessage.COMMAND_EDIT_SUCCESS, task.getDetails()),
                		Palette.MESSAGE_SUCCESS);
                collection.update(task);
                
                ListCommand listCommand = new ListCommand(userInterface, collection, state);
                listCommand.execute();
            }
        }
    }

    @Override
    public void undo() throws Exception {
        if (task == null) {
            throw new NotExecutedException();
        } else {
            task.setDetails(oldTaskDetails.getDetails());
            task.setStartDateTime(oldTaskDetails.getStartDateTime());
            task.setEndDateTime(oldTaskDetails.getEndDateTime());
            collection.update(task);
            
            userInterface.displayMessage(
            		String.format(UIMessage.COMMAND_EDIT_UNDO, task.getDetails()),
            		Palette.MESSAGE_SUCCESS);
        }
    }
}
