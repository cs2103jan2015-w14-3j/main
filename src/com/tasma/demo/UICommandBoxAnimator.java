/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.demo;

import java.util.Timer;
import java.util.TimerTask;

import com.tasma.TasmaUserInterface;

/**
 * Provides the visual animation that look as though the user types in text,
 * but is in fact animated in this class. What a liar.
 * @author Yong Shan Xian <ysx@u.nus.edu>
 */
public class UICommandBoxAnimator {
	
	/**
	 * The default typing speed in characters per second
	 */
	private final static float DEFAULT_SPEED = 24.0f;
	
	/**
	 * The user interface to act out the typing on
	 */
	protected TasmaUserInterface userInterface;
	
	/**
	 * The speed of typing in characters per second
	 */
	protected float speed;
	
	/**
	 * You need a timer to carry out animation right?
	 */
	protected Timer timer;
	
	public UICommandBoxAnimator(TasmaUserInterface userInterface) {
		this(userInterface, DEFAULT_SPEED);
	}
	
	public UICommandBoxAnimator(TasmaUserInterface userInterface, float speed) {
		this.userInterface = userInterface;
		this.speed = speed;
	}
	
	public void animate(String text) {
		animate(text, 0);
	}
	
	public void animate(String text, int start) {
		if (timer != null) {
			timer.cancel();
		}
		timer = new Timer();
		timer.schedule(new TimerTask() {
			int length = start;
			
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
