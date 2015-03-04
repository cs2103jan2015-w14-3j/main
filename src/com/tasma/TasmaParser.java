package com.tasma;

import java.util.ArrayList;

/**
 * @author Manika Agarwal
 *
 */

public class TasmaParser {

	public ArrayList<String> parseCommand(String command, CommandType type) {
		switch (type) {
		case ADD : 
			return parseAdd(command);
		case SEARCH :
			return parseSearch(command);
		case LIST :
			return parseList(command);
		case MARK :
			return parseMark(command);
		case ARCHIVE :
			return parseArchive(command);
		default :
			break;	
		}
		return null;
	}

	private ArrayList<String> parseAdd(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<String> parseSearch(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<String> parseList(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<String> parseMark(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<String> parseArchive(String command) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String args[]) {
		
	}
}
