/**
 * Tasma Task Manager
 */
//@author A0119434H
package com.tasma;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.DateTime;

public class BalloonNotification {
	
	private static final String REMINDER_CAPTION = "Reminder";
	private static final String REMINDER_DEADLINE = "Reminder";
	private static final String REMINDER_TIMED = "Reminder";
	
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
	
	public void scheduleNotifications(List<Task> undoneTasks){
		timer.cancel();
		timer = new Timer();
		for(Task task : undoneTasks){
			TaskType type = task.getType();
			switch(type){
			case DEADLINE:
				scheduleDeadlineNotification(task);
				break;
			case TIMED:
				scheduleTimedTaskNotification(task);
				break;
			case FLOATING:
			default:
				break;
			}
		}
	}
	
	private void scheduleDeadlineNotification(Task task) {
		
		String taskDetail = task.getDetails();
		DateTime startDateTime = task.getStartDateTime();
		
		Date reminderTime = startDateTime.minusHours(1).toDate();
		
		TimerTask timertask = new TimerTask() {
			@Override
			public void run() {
				trayIcon.displayInfo(REMINDER_CAPTION, "The task \"" + taskDetail + "\" is due in 1 hour");
				System.out.println("deadline reminder");
			}
		};
		timer.schedule(timertask, reminderTime);
		System.out.println("deadline reminder scheduled at " + reminderTime.toString());
	}
	
	private void scheduleTimedTaskNotification(Task task) {
		String taskDetail = task.getDetails();
		DateTime startDateTime = task.getStartDateTime();
		
		Date reminderTime = startDateTime.minusHours(1).toDate();
		
		TimerTask timertask = new TimerTask() {
			@Override
			public void run() {
				trayIcon.displayInfo(REMINDER_CAPTION, "The task \"" + taskDetail + "starts in 1 hour");
				System.out.println("timed task reminder");
			}
		};
		timer.schedule(timertask, reminderTime);
		System.out.println("timed task reminder scheduled at " + reminderTime.toString());
	}

}
