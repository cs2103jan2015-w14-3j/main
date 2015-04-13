/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tasma.MockStorage;
import com.tasma.TaskCollection;
import com.tasma.UIMessage;
import com.tasma.ui.MockUserInterface;

public class InvalidCommandTest {
    
    private MockStorage storage;
    
    private TaskCollection collection;
    
    private MockUserInterface userInterface;

    @Before
    public void setUp() throws Exception {
        userInterface = new MockUserInterface();
        storage = new MockStorage();
        collection = new TaskCollection(storage);
        collection.loadFromFile();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testExecute() throws Exception {
        InvalidCommand command = new InvalidCommand(userInterface, collection, "some command");
        command.execute();
        assertEquals(UIMessage.COMMAND_INVALID, userInterface.getLastDisplayedMessage());
    }

}
