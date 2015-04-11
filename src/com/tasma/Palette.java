/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import java.awt.Color;
import java.awt.Font;

/**
 * Provides the colours used by the user interface and commands to tell users (:
 */
public class Palette {
	/**
	 * The default theme blue colour of TASMA
	 * #29ABE2
	 */
	public static final Color THEME_BLUE = new Color(41, 171, 226);
	
	/**
	 * The placeholder text's colour of blue. 
	 * The background of the command box is THEME_BLUE, so we have to use a very light shade of blue
	 */
	public static final Color PLACEHOLDER_TEXT = new Color(162, 216, 232);
	
	/**
	 * The default message colour
	 */
	public static final Color MESSAGE_DEFAULT = new Color(20, 20, 20);
	
	/**
	 * The message colour that shows success
	 */
	public static final Color MESSAGE_SUCCESS = new Color(86, 179, 114);
	
	/**
	 * The message colour that shows a warning
	 */
	public static final Color MESSAGE_WARNING = new Color(255, 145, 0);
	
	/**
	 * The message colour that shows danger
	 */
	public static final Color MESSAGE_DANGER = new Color(217, 71, 61);
	
	/**
	 * The message colour that shows information
	 */
	public static final Color MESSAGE_INFO = new Color(21, 118, 179);
	
	/**
	 * Colour of task that is overdue
	 * Color: Red
	 */
	public static final Color TASK_LIST_OVERDUE = new Color(255, 0, 0);
	
	/**
	 * Color of task that is due today
	 * Color: Orange
	 */
	public static final Color TASK_LIST_TODAY = new Color(255, 145, 0);
	
	/**
	 * Color of floating tasks
	 * Color: Green
	 */
	public static final Color TASK_LIST_FLOATING = new Color(42, 212, 0);
	
	/**
	 * Default task colour
	 */
	public static final Color TASK_LIST_DEFAULT = new Color(80, 80, 80);
	
	/**
	 * Foreground colour of task list header
	 */
	public static final Color TASK_LIST_HEADER_FOREGROUND = new Color (255, 255, 255);
	
	/**
	 * Background colour of task list header
	 */
	public static final Color TASK_LIST_HEADER_BACKGROUND = new Color (8, 155, 218);
	
	/**
	 * The default UI tasks
	 */
	public static final Font UI_FONT_DEFAULT = new Font("Arial", Font.PLAIN, 12);
	
	/**
	 * Tasks list header font
	 */
	public static final Font UI_LIST_HEADER = UI_FONT_DEFAULT.deriveFont(Font.BOLD, 14.0f);
	
	/**
	 * Tasks title font
	 */
	public static final Font UI_TASK_TITLE = UI_FONT_DEFAULT.deriveFont(Font.PLAIN, 16.0f);
	
	/**
	 * Section header fonts
	 */
	public static final Font UI_TASK_SECTION_HEADER = UI_FONT_DEFAULT.deriveFont(Font.BOLD, 20.0f);
	
	/**
	 * Hint frame background color
	 */
	public static final Color HINT_FRAME_BACKGROUND = new Color(252, 232, 104);
	
	/**
	 * Zebra list background 1
	 */
	public static final Color ZEBRA_LIST_BACKGROUND_ONE = Color.WHITE;
	
	/**
	 * Zebra list background 2
	 */
	public static final Color ZEBRA_LIST_BACKGROUND_TWO = new Color(245, 245, 245);
}
