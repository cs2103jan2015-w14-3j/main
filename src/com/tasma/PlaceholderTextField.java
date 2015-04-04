/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;

/**
 * Provides placeholder text functionality - usability importance
 * Otherwise the command box will be a sad empty lonely blue box.
 * @author Sam Yong
 */
@SuppressWarnings("serial")
public class PlaceholderTextField extends JTextField {

    private String placeholder;
    private Color placeholderColor = getDisabledTextColor();

    /**
     * Get the placeholder text
     * @return String
     */
    public String getPlaceholder() {
        return placeholder;
    }
    
	/**
	 * Get the colour of the placeholder text
	 * @return java.awt.Color
	 */
    public Color getPlaceholderColor() {
    	return placeholderColor;
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(placeholderColor);
        g.drawString(placeholder, getInsets().left,
        		pG.getFontMetrics().getMaxAscent() + getInsets().top);
    }

    /**
     * Set the placeholder text
     * @param String
     */
    public void setPlaceholder(final String s) {
        placeholder = s;
    }
    
    /**
     * Set the placeholder text colour
     * @param java.awt.Color
     */
    public void setPlaceholderColor(Color color) {
    	this.placeholderColor = color;
    }

}