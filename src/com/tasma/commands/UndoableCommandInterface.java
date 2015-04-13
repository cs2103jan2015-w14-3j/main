/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

public interface UndoableCommandInterface extends CommandInterface {
    public void undo() throws Exception;
}
