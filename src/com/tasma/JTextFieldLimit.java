package com.tasma;

import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument{
	private int limit;
	
	JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}
	
	JTextFieldLimit(int limit, boolean upper) {
	    super();
	    this.limit = limit;
	}
}
