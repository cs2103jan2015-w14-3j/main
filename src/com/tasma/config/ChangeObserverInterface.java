/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.config;

/**
 * Provides interfacing for a configuration change observer
 */
public interface ChangeObserverInterface {
	
	public void notify(String key, String oldValue, String newValue);
	
}
