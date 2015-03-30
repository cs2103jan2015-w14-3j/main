/**
 * Tasma Task Manager
 */
package com.tasma;

/**
 * Dummy class for testing parser 
 * @author Manika Agarwal
 *
 */

public class ParserMain {
	public static void main(String[] args) {
		Parser parser = new Parser();
		
		try {
			System.out.println("do cs2105 on next mon");
			parser.parse("do cs2105 on next mon");

			System.out.println("do CS2105");
			parser.parse("do CS2105");

			System.out.println("add yay by tues");
			parser.parse("add yay by tues");

			System.out.println("DO CS2105 ON NEXT MON");
			parser.parse("DO CS2105 ON NEXT MON");

			System.out.println("do cs2105 on mon");
			parser.parse("do cs2105 on mon");

			System.out.println("do cs2105 on next mon at ALL");
			parser.parse("do cs2105 on next mon at ALL");

			System.out.println("do cs2105 on mon at ALL");
			parser.parse("do cs2105 on mon at ALL");

			System.out.println("do cs2105 on next friday at ALL");
			parser.parse("do cs2105 on next friday at ALL");

			System.out.println("do cs2105 on monday at ALL");
			parser.parse("do cs2105 on monday at ALL");

			System.out.println("do cs2105 on 24/05/15 at ALL");
			parser.parse("do cs2105 on 24/05/15 at ALL");

			System.out.println("do cs2105 on 22.05.15 at ALL");
			parser.parse("do cs2105 on 22.05.15 at ALL");

			System.out.println("do cs2105 on 22-05-15 at -2pm at ALL");
			parser.parse("do cs2105 on 22-05-15 at -2pm at ALL");
			
			System.out.println("");
			parser.parse("");
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		}
	}
}
