package com.tasma.commands;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.tasma.Task;

public class TaskListSorter {

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
