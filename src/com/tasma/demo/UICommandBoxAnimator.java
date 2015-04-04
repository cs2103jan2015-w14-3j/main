/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.demo;

import java.util.Timer;
import java.util.TimerTask;

import com.tasma.TasmaUserInterface;

public class UICommandBoxAnimator {
	
	private final static float DEFAULT_SPEED = 20.0f;
	protected TasmaUserInterface userInterface;
	protected float speed;
	protected Timer timer;
	
	public UICommandBoxAnimator(TasmaUserInterface userInterface) {
		this(userInterface, DEFAULT_SPEED);
	}
	
	public UICommandBoxAnimator(TasmaUserInterface userInterface, float speed) {
		this.userInterface = userInterface;
		this.speed = speed;
	}
	
	public void animate(String text) {
		if (timer != null) {
			timer.cancel();
		}
		timer = new Timer();
		timer.schedule(new TimerTask() {
			int length = 0;
			
			@Override
			public void run() {
				++length;
				userInterface.editCmdDisplay(text.substring(0, length));
				if (length >= text.length()) {
					this.cancel();
				}
			}
		}, 0, (long)(1 / speed * 1000));
	}
}
