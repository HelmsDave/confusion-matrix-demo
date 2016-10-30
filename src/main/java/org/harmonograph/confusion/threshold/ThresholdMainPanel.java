/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.threshold;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory; 
import javax.swing.border.TitledBorder;

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
    
    ///** Controls panel. */
    //protected final ThresholdControls m_controls;
    
    /** Simple Constructor. */
    public ThresholdMainPanel() {
        
        m_panel = new JPanel();
        m_panel.setLayout(new GridBagLayout());
        
        final TitledBorder title = BorderFactory.createTitledBorder("Measurement");
        m_panel.setBorder(title);        
        m_panel.setToolTipText(
                "<html>Measurement Space<p>with Noise and Signal PDF<p>" + 
                        "Adjust controls to change PDF and Threshold</html>");
        
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
//        {
//            m_controls = new ThresholdControls(m_canvas);
//            final GridBagConstraints c = new GridBagConstraints();
//            c.gridx = 1;
//            c.gridy = 2;
//            c.weightx = 0.5f;          
//            c.fill = GridBagConstraints.HORIZONTAL;
//            m_panel.add(m_controls.getPanel(), c);
//        }
        
    }
    
    /**
     * Get JPanel
     * @return JPanel
     */
    public JPanel getPanel() {
        return m_panel;
    }
    
}
