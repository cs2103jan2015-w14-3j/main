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

import com.tasma.ui.TrayIcon;

public class BalloonNotification {

	private static final String REMINDER_CAPTION = "Reminder";
	private static final String REMINDER_DEADLINE = "The task \"%s\" is due in 1 hour";
	private static final String REMINDER_TIMED = "The task \"%s\" starts in 1 hour";

	private Timer timer;
	private TaskCollection collection;
	private TrayIcon trayIcon;

	BalloonNotification(TaskCollection collection, TrayIcon trayIcon) {
		timer = new Timer();
		this.collection = collection;
		this.trayIcon = trayIcon;
	}

	public void setup() {
		collection.notification = this;
	}

	public void updateNotifications(List<Task> undoneTasks) {
		timer.cancel();
		timer = new Timer();
		for (Task task : undoneTasks) {
			TaskType type = task.getType();
			switch (type) {
			case DEADLINE:
			case TIMED:
				scheduleNotification(task, type);
				break;
			case FLOATING:
			default:
				break;
			}
		}
	}

	private class Reminder extends TimerTask {

		String reminderMessage;

		Reminder(String message) {
			reminderMessage = message;
		}

		@Override
		public void run() {
			trayIcon.displayInfo(REMINDER_CAPTION, reminderMessage);
		}
	}

	private void scheduleNotification(Task task, TaskType type) {

		String taskDetail = task.getDetails();
		DateTime startDateTime = task.getStartDateTime();
		Date reminderTime = startDateTime.minusHours(1).toDate();
		String reminderMessage = "";

		if (type == TaskType.DEADLINE) {
			reminderMessage = String.format(REMINDER_DEADLINE, taskDetail);
		} else if (type == TaskType.TIMED) {
			reminderMessage = String.format(REMINDER_TIMED, taskDetail);
		}

		TimerTask reminder = new Reminder(reminderMessage);
		timer.schedule(reminder, reminderTime);

	}
}
