/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.tasma.Task;

/**
 * Provides chronological sorting of tasks according to their start date.
 * Floating tasks can be handled too.
 * @author Yong Shan Xian <ysx@u.nus.edu>
 */
public class TaskListSorter {

	/**
	 * Performs chronological sorting on the tasks according to
	 * their start date in ascending order. 
	 * @param tasks The list of tasks to be sorted
	 */
	public static void sort(List<Task> tasks) {
		Collections.sort(tasks, new Comparator<Task>() {
		    @Override
		    public int compare(Task t1, Task t2) {
		    	if (t1.getStartDateTime() == null && t2.getStartDateTime() == null) {
		    		return 0;
		    	} else if (t1.getStartDateTime() == null) {
		    		return -1;
		    	} else if (t2.getStartDateTime() == null) {
		    		return 1;
		    	}
		    	return t1.getStartDateTime().compareTo(t2.getStartDateTime());
		    }
		}); 
	}

}
