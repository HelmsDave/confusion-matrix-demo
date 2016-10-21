/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.threshold;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
    
    /** Canvas for PDF plot. */
    protected final ThresholdCanvas m_canvas;
    
    /** Simple Constructor. */
    public ThresholdMainPanel() {
        
        m_panel = new JPanel();
        m_panel.setLayout(new GridBagLayout());
        
        {
            m_canvas = new ThresholdCanvas();
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 0.5f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_canvas.getPanel(), c);
        }
        
        
        
    }
    
    /**
     * Get JPanel
     * @return JPanel
     */
    public JPanel getPanel() {
        return m_panel;
    }
    
}
