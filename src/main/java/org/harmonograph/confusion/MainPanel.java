/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import org.harmonograph.confusion.matrix.MatrixMainPanel;
import org.harmonograph.confusion.metrics.MetricsMainPanel;
import org.harmonograph.confusion.threshold.ThresholdMainPanel;

/**
 * Top Level Panel for Demo application
 * shared between standalone application
 * and Applet.
 * 
 * @author Dave
 */
public class MainPanel {
    
    /** Top level panel for demo application. */
    protected final JPanel m_panel;
   
    /** Threshold panel, top left. */
    protected final ThresholdMainPanel m_thresholdPanel;
    
    /** Confusion Matrix panel, top right. */
    protected final MatrixMainPanel m_matrixPanel;
    
    /** Metrics Panel, all tabs, bottom. */
    protected final MetricsMainPanel m_metricsPanel;
    
    /** Simple constructor. Simply holds main panels. */
    public MainPanel() {
        
        m_panel = new JPanel();
        m_panel.setMinimumSize(new Dimension(400, 400));
       
        m_panel.setLayout(new GridBagLayout());
        
        {
            m_thresholdPanel = new ThresholdMainPanel();
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 1;
            c.gridheight = 2;
            c.weightx = 0.3f;
            c.weighty = 0.5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_thresholdPanel.getPanel(), c);
        }
        
        {
            m_matrixPanel = new MatrixMainPanel();
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 1;
            c.weightx = 0.7f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_matrixPanel.getPanel(), c);
        }        
        
        {
            m_metricsPanel = new MetricsMainPanel();
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 2;
            c.weightx = 0.7f;
            c.weighty = 0.5f;            
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_metricsPanel.getPanel(), c);
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
