/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tasma.config.Config;

/**
 * Provides near-filesystem level abstraction of the task storage file
 * plus serialisation and unserialisation
 */
public class Storage {
	private static final Logger logger = Log.getLogger( Storage.class.getName() );
	
	/**
	 * The file name of the task storage file
	 */
	public static final String FILENAME = "tasks.json";
	
	private String path;
	
	public Storage() throws Exception {
		this(Config.getInstance().getProperty("storage"));
	}
	
	public Storage(String path) throws Exception {
		this.path = path;
	}
	
	/**
	 * Saves the list of tasks into the default storage file.
	 * @param tasks The list of tasks to be saved
	 * @throws IOException Thrown when there is a problem trying to save data into the file.
	 */
	public void save(LinkedList<Task> tasks) throws IOException {
		logger.log(Level.FINE, "Performing a save on {0} tasks to file", tasks.size());
		final Gson gson = Converters.registerDateTime(new GsonBuilder()).create();
		final String json = gson.toJson(tasks);
		byte[] utf8JsonString = json.getBytes("UTF8");
    
	    try {
	    	File file = new File(path, FILENAME);
	    	FileOutputStream stream = new FileOutputStream(file);
		    stream.write(utf8JsonString);  
		    stream.close();
	    } catch (IOException e) {  
			throw e;
	    }
	}

	/**
	 * Loads the list of tasks from the default storage file.
	 * @return Returns the list of tasks that was loaded from the file
	 * @throws IOException Thrown when there is a problem trying to save data into the file.
	 */
	public LinkedList<Task> load() throws IOException {
		logger.log(Level.FINE, "Performing load from file");
		LinkedList<Task> tasks = new LinkedList<Task>();
		
		File file = new File(path, FILENAME);
		if (file.exists()) {
			try {
				byte[] utf8JsonString = java.nio.file.Files.readAllBytes(file.toPath());
				final Gson gson = Converters.registerDateTime(new GsonBuilder()).create();
				String json = new String(utf8JsonString);
				tasks = gson.fromJson(json, new TypeToken<LinkedList<Task>>() {}.getType());
			}catch(IOException e) {
				throw e;
			} 
		}
		return tasks;
	}
}
