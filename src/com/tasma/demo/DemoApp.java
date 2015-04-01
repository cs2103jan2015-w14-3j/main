package com.tasma.demo;

import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.Stack;
import javax.swing.KeyStroke;
import com.tasma.Controller;
import com.tasma.MockStorage;
import com.tasma.TaskCollection;
import com.tasma.TasmaApp;
import com.tasma.TasmaGUI;
import com.tasma.TasmaUserInterface;
import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;

public class DemoApp implements Runnable {
	
	private Controller controller;
	private MockStorage storage;
	private TaskCollection collection;
	private TasmaUserInterface frame; 

	public static void main(String[] main) {
		EventQueue.invokeLater(new TasmaApp());
	}
	
	public DemoApp() {
		try {
			frame = new TasmaGUI();
			storage = new MockStorage();
			collection = new TaskCollection(storage);
			controller = new Controller(collection);
		} catch (Exception e) {
			
		}
	}
	
	public void run() {
		try {
			frame.initialize(controller);
			controller.initialize();
			controller.executeInput("list");
			
			frame.show();

			Provider provider = Provider.getCurrentProvider(true);
			provider.register(KeyStroke.getKeyStroke("alt shift X"), new HotKeyListener() {

				@Override
				public void onHotKey(HotKey hotKey) {
					frame.show();
				}
	        });
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected class Actor {
		private Stack<Runnable> actionsCompleted = new Stack<Runnable>();
		private LinkedList<Runnable> actionsAvailable = new LinkedList<Runnable>();
		
		public void initialize() {
		}
		
		public void previous() {
			if (actionsCompleted.size() > 0) {
				Runnable action = actionsCompleted.pop();
				actionsAvailable.push(action);
				action.run();
			}
		}
		
		public void next() {
			if (actionsAvailable.size() > 0) {
				Runnable action = actionsAvailable.poll();
				actionsCompleted.push(action);
				action.run();
			}
		}
	}
}
