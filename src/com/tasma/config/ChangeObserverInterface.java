package com.tasma.config;

public interface ChangeObserverInterface {
	public void notify(String key, String oldValue, String newValue);
}
