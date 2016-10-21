/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.messages;

import java.awt.Color;

/**
 * Colors for plots.
 * @author Dave
 */
public class ColorScheme {
    
    /** Private constructor.  */
    private ColorScheme() {
    }
    
    /** Color of True Positive data. */
    public static final Color TRUE_POS_COLOR = Color.GREEN;
    /** Color of True Negative data. */
    public static final Color TRUE_NEG_COLOR = Color.BLUE;
    /** Color of True Positive data. */
    public static final Color FALSE_POS_COLOR = Color.YELLOW;
    /** Color of False Negative data. */
    public static final Color FALSE_NEG_COLOR = Color.RED;
    /** Color of threshold division line. */
    public static final Color THRESH_COLOR = Color.BLACK;    
}
