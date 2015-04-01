package com.tasma.demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import com.tasma.Controller;
import com.tasma.MockStorage;
import com.tasma.TaskCollection;
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
		EventQueue.invokeLater(new DemoApp());
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
			
			Provider provider = Provider.getCurrentProvider(true);
			provider.register(KeyStroke.getKeyStroke("alt shift X"), new HotKeyListener() {

				@Override
				public void onHotKey(HotKey hotKey) {
					frame.show();
				}
	        });
			
			Actor actor = new Actor();
			actor.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected class Actor {
		private Stack<Runnable> actionsCompleted = new Stack<Runnable>();
		private LinkedList<Runnable> actionsAvailable = new LinkedList<Runnable>();
		private JFrame demoControl = new JFrame();
		public void initialize() {
			prepareWindow();
			prepareIV();
			loadActions();
		}
		
		protected void prepareIV() {
			
		}
		
		protected void prepareWindow() {
			Actor thisActor = this;
			demoControl.setLayout(new BorderLayout());
			demoControl.setResizable(false);
			demoControl.setUndecorated(true);
			demoControl.setSize(40, 60);
			JButton exitButton = new JButton("X");
			exitButton.setSize(40, 20);
			demoControl.getContentPane().add(exitButton, BorderLayout.PAGE_START);
			exitButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			
			JButton nextButton = new JButton(">");
			exitButton.setSize(40, 20);
			demoControl.getContentPane().add(nextButton, BorderLayout.PAGE_END);
			nextButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					thisActor.next();
				}
			});

			demoControl.setVisible(true);
		}
		
		public void loadActions() {
			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.show();
				}
			});
				}
			});
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
