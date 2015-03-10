package com.tasma;

/**
 * Dummy class for testing parser 
 * @author Manika
 *
 */


public class ParserMain {
	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.parse("do cs2105 on next mon");
		parser.parse("do CS2105");
		parser.parse("add yay by tues");
		parser.parse("DO CS2105 ON NEXT MON");
		parser.parse("do cs2105 on mon");
		parser.parse("do cs2105 on next mon at ALL");
		parser.parse("do cs2105 on mon at ALL");
		parser.parse("do cs2105 on next monday at ALL");
		parser.parse("do cs2105 on monday at ALL");
	}
}
