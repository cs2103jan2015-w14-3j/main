/**
 * Tasma Task Manager
 */
//@author A0118888J
package com.tasma;

public class InvalidInputException extends Exception {

    private static final long serialVersionUID = -2968409273587420542L;

    public InvalidInputException() {
    }

    // Constructor that accepts a message
    public InvalidInputException(String message) {
        super(message);
    }

}
