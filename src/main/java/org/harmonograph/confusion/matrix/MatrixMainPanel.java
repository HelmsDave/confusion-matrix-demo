/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.matrix;

import javax.swing.JPanel;

/**
 * The Matrix panel shows a simple confusion matrix 
 * with margins and labels.
 * @author Dave
 */
public class MatrixMainPanel {
   
    /** Main Panel for Confusion Matrix. */
    protected final JPanel m_panel;    
    
    /** Simple Constructor. */
    public MatrixMainPanel() {
        
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
