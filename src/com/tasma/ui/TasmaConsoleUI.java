/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.ui;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.tasma.Controller;
import com.tasma.Task;

/**
 * This is an alternative UI for testing non-GUI stuff.
 */
public class TasmaConsoleUI implements TasmaUserInterface {

    /**
     * The controller of the console UI
     */
    private Controller controller;
    
    /**
     * The task list header
     */
    protected String headerText;
    
    public void initialize(Controller controller) throws Exception {
        this.controller = controller;
        this.controller.setUserInterface(this);
    }
    
    protected void outputRow(Object value) {
        if (value instanceof String) {
            String sectionHeader = value.toString();
            System.out.println("\n-- " + sectionHeader + " " +
            padding(sectionHeader, 80, '-'));
        } else if (value instanceof Map.Entry) {
            @SuppressWarnings("unchecked")
            Map.Entry<Integer, Task> entry = (Map.Entry<Integer, Task>)value;
            int taskIndex = entry.getKey();
            Task task = entry.getValue();
            
            String lineOutput = "";
            lineOutput += (taskIndex + 1) + ".\t";
            lineOutput += task.getDetails() + "\n";
            if (task.isDone() || task.getStartDateTime() != null) {
                lineOutput += "\t";
                if (task.isDone()) {
                    lineOutput += "[DONE] ";
                }
                if (task.getStartDateTime() != null) {
                    lineOutput += task.getStringStartDateTime();
                }
                lineOutput += "\n";
            }
            System.out.print(lineOutput);
        }
    }
    
    @Override
    public void displayTasks(List<Task> tasks) {
        if (headerText != null) {
            System.out.println("== " + headerText + " ==");
        }
        List<Object> finalList = UITaskListSorter.sort(tasks);
        for(Object value: finalList) {
            outputRow(value);
        }
    }
    
    @Override
    public void displayMessage(String message) {
        displayMessage(message, null);
    }

    @Override
    public void displayMessage(String message, Color color) {
        System.out.println("INFO: " + message);
    }
    
    public void editCmdDisplay (String task) {
    }

    @SuppressWarnings("resource")
    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("Command: ");
            String input = sc.nextLine();
            controller.executeInput(input);
        }
    }

    @Override
    public void hide() {
        
    }

    @Override
    public boolean isVisible() {
        return false;
    }
    
    private static String padding( String text, int n, char ch ) {
        return new String(new char[n - text.length()]).replace('\0', ch);
    }

    @Override
    public void setHeader(String header) {
        this.headerText = header;
    }
}
