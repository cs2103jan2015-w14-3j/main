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
	private UICommandBoxAnimator commandBoxAnimator;

	public static void main(String[] main) {
		EventQueue.invokeLater(new DemoApp());
	}
	
	public DemoApp() {
		try {
			frame = new TasmaGUI();
			storage = new MockStorage();
			collection = new TaskCollection(storage);
			controller = new Controller(collection);
			commandBoxAnimator = new UICommandBoxAnimator(frame);
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
			loadActions();
		}
		
		protected void prepareWindow() {
			Actor thisActor = this;
			demoControl.setLayout(new BorderLayout());
			demoControl.setResizable(false);
			demoControl.setUndecorated(true);
			demoControl.setSize(60, 60);
			demoControl.setAlwaysOnTop(true);
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
					controller.executeInput("tutorial");
					frame.show();
				}
			});
			
			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					controller.executeInput("a bring mom to visit doctor on next monday");
					controller.executeInput("a settle bank account matters tomorrow");
					controller.executeInput("a bring dog to vet for check up");
					controller.executeInput("list");
					frame.displayMessage("");
					commandBoxAnimator.animate("add");
				}
			});
			
			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					commandBoxAnimator.animate("add return library book on next wednesday", 4);
				}
			});
			
			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.editCmdDisplay("");
					controller.executeInput("add return library book on next wednesday");
				}
			});
			
			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.displayMessage("");
					commandBoxAnimator.animate("a");
				}
			});
			
			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.displayMessage("");
					commandBoxAnimator.animate("a buy birthday gift for hui qi", 2);
				}
			});
			
			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.editCmdDisplay("");
					controller.executeInput("a buy birthday gift for hui qi");
				}
			});
			
			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					commandBoxAnimator.animate("help");
				}
			});
			
			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.displayMessage("");
					commandBoxAnimator.animate("help add", 5);
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.editCmdDisplay("");
					controller.executeInput("help add");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.displayMessage("");
					commandBoxAnimator.animate("edit 5");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.editCmdDisplay("");
					controller.executeInput("edit 5");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.displayMessage("");
					commandBoxAnimator.animate("edit 5 buy a cushion as birthday gift for hui qi");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.editCmdDisplay("");
					controller.executeInput("edit 5 buy a cushion as birthday gift for hui qi");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.displayMessage("");
					commandBoxAnimator.animate("mark 4");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.editCmdDisplay("");
					controller.executeInput("mark 4");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.displayMessage("");
					commandBoxAnimator.animate("undo");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.editCmdDisplay("");
					controller.executeInput("undo");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.displayMessage("");
					commandBoxAnimator.animate("mark 5");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.editCmdDisplay("");
					controller.executeInput("mark 5");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					controller.executeInput("add do spring cleaning on Saturday");
					controller.executeInput("add plan a short trip to batam");
					controller.executeInput("add sign up for standard chartered marathon by next monday");
					controller.executeInput("add send camera for repairs");
					controller.executeInput("add get curry chicken from aunt for mum");
					controller.executeInput("add take IPPT at Maju Camp");
					controller.executeInput("add find cheap flights to Taiwan");
					controller.executeInput("add get pants tailored for wedding dinner");
					controller.executeInput("add try make DIY photo frames");
					controller.executeInput("add buy contact lens solution");
					controller.executeInput("add bring cousins out to dinner on sunday");
					controller.executeInput("add pay for mobile bills");
					controller.executeInput("add hire gardener to tidy up the lawn");
					controller.executeInput("done 8");
					
					controller.executeInput("list");
					frame.displayMessage("");
					frame.editCmdDisplay("");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.displayMessage("");
					commandBoxAnimator.animate("search dinner");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.editCmdDisplay("");
					controller.executeInput("search dinner");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.displayMessage("");
					commandBoxAnimator.animate("delete 2");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.editCmdDisplay("");
					controller.executeInput("delete 2");
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.editCmdDisplay("");
					frame.hide();
				}
			});

			actionsAvailable.offer(new Runnable() {
				@Override
				public void run() {
					frame.show();
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
