/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.config;

/**
 * Provides interfacing for a configuration change observer
 * @author Yong Shan Xian <ysx@u.nus.edu>
 */
public interface ChangeObserverInterface {
	public void notify(String key, String oldValue, String newValue);
}
