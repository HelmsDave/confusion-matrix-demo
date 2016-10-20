/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import org.harmonograph.confusion.matrix.MatrixMainPanel;
import org.harmonograph.confusion.metrics.MetricsMainPanel;
import org.harmonograph.confusion.threshold.ThresholdMainPanel;

/**
 * Top Level Frame for demo application.
 * @author Dave
 */
public class MainFrame {
    
    /** Top level frame for demo application. */
    protected final JFrame m_frame;
   
    /** Threshold panel, top left. */
    protected final ThresholdMainPanel m_thresholdPanel;
    
    /** Confusion Matrix panel, top right. */
    protected final MatrixMainPanel m_matrixPanel;
    
    /** Metrics Panel, all tabs, bottom. */
    protected final MetricsMainPanel m_metricsPanel;
    
    /** Simple constructor. Simply holds main panels. */
    public MainFrame() {
        
        m_frame = new JFrame("Confusion Matrix Demo");
        m_frame.setMinimumSize(new Dimension(400, 400));
        
        m_frame.setLayout(new GridBagLayout());
        
        {
            m_thresholdPanel = new ThresholdMainPanel();
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 1;
            c.fill = GridBagConstraints.BOTH;
            m_frame.add(m_thresholdPanel.getPanel(), c);
        }
        
        {
            m_matrixPanel = new MatrixMainPanel();
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 1;
            c.fill = GridBagConstraints.BOTH;
            m_frame.add(m_matrixPanel.getPanel(), c);
        }        
        
        {
            m_metricsPanel = new MetricsMainPanel();
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 2;
            c.gridwidth = 2;
            c.fill = GridBagConstraints.BOTH;
            m_frame.add(m_metricsPanel.getPanel(), c);
        }         
    }
    
    /**
     * Get JFrame.
     * @return JFrame
     */
    public JFrame getFrame() {
        return m_frame;
    }
    
}