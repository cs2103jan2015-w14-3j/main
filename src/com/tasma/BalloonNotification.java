package com.tasma;

import java.util.Timer;

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
}
