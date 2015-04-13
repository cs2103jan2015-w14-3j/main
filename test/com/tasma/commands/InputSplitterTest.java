/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InputSplitterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSplitting() {
        InputSplitter splitter = new InputSplitter("test 123 maybe will not go wrong");
        assertEquals("test", splitter.next());
        assertEquals("123", splitter.next());
        assertEquals("maybe", splitter.next());
        assertEquals("will not go wrong", splitter.remainder());
    }

    @Test
    public void testSplitting2() {
        InputSplitter splitter = new InputSplitter("test 123 maybe will not go wrong");
        assertEquals("test 123 maybe will not go wrong", splitter.remainder());
    }

}
