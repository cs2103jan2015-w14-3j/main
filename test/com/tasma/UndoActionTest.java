package com.tasma;

import static org.junit.Assert.*;

import org.junit.Test;

public class UndoActionTest {

	@Test
	public void test() {
		Task task = new Task();
		
		UndoAction action = new UndoAction(CommandType.ADD, task);
		assertEquals(task, action.getTask());
		assertEquals(CommandType.ADD, action.getCommand());
	}

}
