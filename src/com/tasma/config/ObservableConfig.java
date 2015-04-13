/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.config;

import java.util.LinkedList;

/**
 * Provides abstraction for a config that can be observed using
 * the Observer Pattern
 */
public abstract class ObservableConfig {
    
    /**
     * The list of observers to notify later
     */
    private LinkedList<ChangeObserverInterface> observers = new LinkedList<ChangeObserverInterface>();

    /**
     * Add an observer to the list of observers to keep updated
     * @param observer The observer to watch the config
     */
    public void addObserver(ChangeObserverInterface observer) {
        observers.add(observer);
    }
    
    /**
     * Notify all observers of a change in the configuration values
     * @param key The key of the configuration that was changed
     * @param oldValue The old value of the configuration key
     * @param newValue The new value of the configuration key
     */
    protected void notifyObservers(String key, String oldValue, String newValue) {
        for(ChangeObserverInterface observer: observers) {
            observer.notify(key, oldValue, newValue);
        }
    }

}
