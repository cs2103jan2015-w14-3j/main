package com.tasma.config;

import java.util.LinkedList;

public abstract class ObservableConfig {
	
	private LinkedList<ChangeObserverInterface> observers = new LinkedList<ChangeObserverInterface>();

	public void addObserver(ChangeObserverInterface observer) {
		observers.add(observer);
	}
	
	protected void notifyObservers(String key, String oldValue, String newValue) {
		for(ChangeObserverInterface observer: observers) {
			observer.notify(key, oldValue, newValue);
		}
	}

}
