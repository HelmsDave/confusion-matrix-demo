/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.threshold;

import javax.swing.JPanel;

/**
 * The Threshold Panel shows a plot of the signal 
 * level being tested with a probability distribution
 * function graphic using a simple Gaussian distribution.
 * 
 * @author Dave
 */
public class ThresholdMainPanel {
    
    /** Main Panel for Threshold Plot. */
    protected final JPanel m_panel;
    
    /** Simple Constructor. */
    public ThresholdMainPanel() {
        
        m_panel = new JPanel();
    }
    
    /**
     * Get JPanel
     * @return JPanel
     */
    public JPanel getPanel() {
        return m_panel;
    }
    
}
