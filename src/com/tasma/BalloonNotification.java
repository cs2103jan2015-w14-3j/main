/**
 * Tasma Task Manager
 */
//@author A0119434H
package com.tasma;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BalloonNotification {
	
	private Timer timer;
	private TaskCollection collection;
	private TrayIcon trayIcon;
	
	BalloonNotification(TaskCollection collection, TrayIcon trayIcon) {
		timer = new Timer();
		this.collection = collection;
		this.trayIcon = trayIcon;
	}
	
	public void setup(){
		collection.notification = this;
	}
	
	public void sheduleNotifications(List<Task> undoneTasks){
		for(Task task : undoneTasks){
			TaskType type = task.getType();
			switch(type){
			case DEADLINE:
				sheduleDeadlineNotification(task);
				break;
			case TIMED:
				sheduleTimedTaskNotification(task);
				break;
			case FLOATING:
			default:
				break;
			}
		}
	}
	
	private void sheduleDeadlineNotification(Task task) {
		System.out.println("deadline");
		
	}
	
	private void sheduleTimedTaskNotification(Task task) {
		System.out.println("timed");
	}

}
